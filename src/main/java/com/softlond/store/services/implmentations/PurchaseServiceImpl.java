package com.softlond.store.services.implmentations;

import com.softlond.store.dto.*;
import com.softlond.store.repositories.contracts.ICategoryRepository;
import com.softlond.store.repositories.contracts.ICustomerRepository;
import com.softlond.store.repositories.contracts.IProductRepository;
import com.softlond.store.repositories.contracts.IPurchaseRepository;
import com.softlond.store.services.contracts.IPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements IPurchaseService {

    private final IPurchaseRepository iPurchaseRepository;
    private final IProductRepository iProductRepository;
    private final ICustomerRepository iCustomerRepository;
    private final ICategoryRepository iCategoryRepository;

    private double totalPurchase = 0;

    private final Double CONST_DISCOUNT_COMPANY_DATE = 0.05; // 5%
    private final Double CONST_DISCOUNT_COMPANY_GAME = 0.10; // 10%
    private final Integer CONST_ATTEMPTS_MAX_LOTTERY = 2;

    @Override
    public List<PurchaseDto> getAll() {
        return iPurchaseRepository.getAll();
    }

    @Override
    public Optional<PurchaseDto> getById(Long purchaseId) {
        return iPurchaseRepository.getById(purchaseId);
    }

    @Override
    public List<PurchaseDto> getByCodePurchase(String codePurchase) {
        return iPurchaseRepository.getByCodePurchase(codePurchase);
    }

    @Override
    public List<PurchaseDto> getByCustomerCardId(Integer customerCardId) {
        return iPurchaseRepository.getByCustomerCardId(customerCardId);
    }

    /**
     * Obener lista de compra por un rango de fechas
     * @param dateInitial Fecha inicio del rango
     * @param dateFinal Fecha final del rango
     * @return Lista de compras dado su rango de fecha
     */
    @Override
    public List<PurchaseDto> getByDatePurchaseBetween(LocalDate dateInitial, LocalDate dateFinal) {
        return iPurchaseRepository.getByDatePurchaseBetween(dateInitial, dateFinal);
    }

    /**
     * Obtener una compra por usuario y un rango de fecha
     * @param customerCardId Identificacion(cedula) de cliente a buscar
     * @param dateInitial Fecha inical del rango
     * @param dateFinal Fecha final del rango
     * @return Lista de compras del cliente
     */
    @Override
    public List<PurchaseDto> getCustomerCardIdAndDatePurchaseBetween(Integer customerCardId, LocalDate dateInitial, LocalDate dateFinal) {
        List<PurchaseDto> purchaseDtoList = iPurchaseRepository.getByCustomerCardIdAndDatePurchaseBetween(customerCardId, dateInitial, dateFinal);
        if (purchaseDtoList.isEmpty()) {
            throw new RuntimeException("El usuario no tiene compras");
        }

        return purchaseDtoList;
    }

    @Override
    public PurchaseDto save(PurchaseDto purchaseDto) {

        Optional<CustomerRequestDto> customerRequestDto = iCustomerRepository.getByCardId(purchaseDto.getCustomerCardId());

        if(customerRequestDto.isPresent()) {

            totalPurchase = 0;
            ProductRequestDto productRequestDto = new ProductRequestDto();

            /**
             * Disminuir el Stock de los productos
             */
            purchaseDto.getPurchaseDetails().stream().forEach(purchaseDetailsDto -> {

                ProductDto productDto = iProductRepository.getById(purchaseDetailsDto.getProductId()).get();

                //  Actualiza el stock de los productos comprados
                int newStock = productDto.getStock() - purchaseDetailsDto.getQuantityProduct();
                productDto.setStock(newStock);

                // total de la compra
                totalPurchase += purchaseDetailsDto.getTotalPriceProducts();

                productRequestDto.setId(productDto.getId());
                productRequestDto.setName(productDto.getName());
                productRequestDto.setPrice(productDto.getPrice());
                productRequestDto.setStock(productDto.getStock());
                productRequestDto.setActive(productDto.getActive());

                List<CategoryDto> categoryDto = iCategoryRepository.getByName(productDto.getCategoryName());

                for (CategoryDto category: categoryDto) {
                    int categoryId = Long.valueOf(category.getId()).intValue();
                    productRequestDto.setCategoryId(categoryId);
                }
                /**
                 * Guardamos los productos con el stock modificado
                 */
                iProductRepository.save(productRequestDto);
            });

            /**
             * Descuento por Fecha
             */
            double discountCompanyDate = calculateDiscountToDateCompany(purchaseDto.getCustomerCardId(), totalPurchase);

            System.out.println("Sin decuento fecha => " +purchaseDto.getTotalPrice());
            if (discountCompanyDate != 0) {
                purchaseDto.setTotalPrice(discountCompanyDate);
                purchaseDto.setQuantityDiscountForPurchaseMonth((CONST_DISCOUNT_COMPANY_DATE *100));
            } else {
                purchaseDto.setTotalPrice(totalPurchase);
                purchaseDto.setQuantityDiscountForPurchaseMonth(0.0);
            }

            System.out.println("con decuento fecha => " +purchaseDto.getTotalPrice());

            /**
             * Descuento por sorteo
             */
            int countAttempts = 0;
            while(countAttempts < CONST_ATTEMPTS_MAX_LOTTERY) {
                double discountLottery = calculateDiscountGame(purchaseDto.getNumberLotteryToDiscount(), purchaseDto.getTotalPrice());
                ++countAttempts;
                if (discountLottery != 0) {
                    // ESTE YA TIENE EL DESCUENTO DE LA FECHA
                    purchaseDto.setTotalPrice(discountLottery);
                    purchaseDto.setWinnerGameDiscount("Ganador!!");
                    purchaseDto.setQuantityDiscountLottery((CONST_DISCOUNT_COMPANY_GAME*100));
                    break;
                } else {
                    purchaseDto.setWinnerGameDiscount("No ganaste el sorteo");
                    purchaseDto.setQuantityDiscountLottery(0.0);
                }
            }

            // GUARDA LA COMPRA CON LOS DESCUENTOS
            iPurchaseRepository.save(purchaseDto);

        } else {
            throw new RuntimeException("El usuario no existe, por favor registrate");
        }

        purchaseDto.setDatePurchase(LocalDateTime.now());
        return purchaseDto;
    }

    /**
     * Descuento por fecha de compras mayor a 1 millon dentro de 31 dias
     * @param cardId Identificacion de cliente a buscar
     * @param pricesTotal Precio total de la compra a calcular
     * @return Precio total con su descuento
     */
    private Double calculateDiscountToDateCompany(Integer cardId, Double pricesTotal) {
        double discountCompany = 0;
        LocalDateTime currentDate = LocalDateTime.now();

        // Convertir LocalDateTime a LocalDate
        LocalDate dateNow = currentDate.toLocalDate();

        //LocalDate fechaPasada = LocalDate.of(2023, 7, 15);

        List<PurchaseDto> purchaseDtoList = getAll();

        System.out.println("Tamaño lista " +purchaseDtoList.size());

        for(PurchaseDto purchaseDto: purchaseDtoList) {
            if(purchaseDto.getCustomerCardId().equals(cardId) && purchaseDto.getTotalPrice() > 1000000) {

                LocalDateTime purchaseDate  = purchaseDto.getDatePurchase();
                LocalDate datePurchaseHistory = purchaseDate.toLocalDate();
                long diferenciaMeses = ChronoUnit.MONTHS.between(datePurchaseHistory, dateNow);

                if (diferenciaMeses == 1) {
                    System.out.println("fecha con 1 mes de diferencia => Ahora "+ dateNow +" Resgitrada BD " + datePurchaseHistory);
                    double discount = pricesTotal * CONST_DISCOUNT_COMPANY_DATE;
                    discountCompany = pricesTotal - discount;
                    break;
                }
            }
        }
        return discountCompany;
    }

    /**
     * Descuento de sorteo
     * @param numberLotteryToDiscount Numero de loteria elegido por el cliente a participar en el sorteo
     * @param pricesTotal Precio total de la compra a calcular
     * @return Precio total con su descuento del concurso
     */
    private Double calculateDiscountGame(Integer numberLotteryToDiscount, Double pricesTotal) {
        double priceTotalDiscountCompany = 0;
        int numberLottery = generatedNumberLotteryGame();

        if(numberLotteryToDiscount == numberLottery) {
            double discount = pricesTotal * CONST_DISCOUNT_COMPANY_GAME;
            priceTotalDiscountCompany = pricesTotal - discount;
        }
        return priceTotalDiscountCompany;
    }

    /**
     * Generar el numero random para el sorteo
     * @return numero de loteria
     */
    private Integer generatedNumberLotteryGame() {
            Random random = new Random();
            return random.nextInt((4));
    }

    @Override
    public Optional<PurchaseDto> update(PurchaseDto purchaseDto) {
        if (getById(purchaseDto.getId()).isEmpty()) {
            return Optional.empty();
        }
        iPurchaseRepository.save(purchaseDto);
        return Optional.of(purchaseDto);
    }

    @Override
    public Boolean delete(Long purchaseId) {
        if(getById(purchaseId).isEmpty()) {
            return false;
        }

        iPurchaseRepository.delete(purchaseId);
        return true;
    }
}