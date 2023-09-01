package com.softlond.store.controllers;

import com.softlond.store.constants.CustomerApiConstants;
import com.softlond.store.dto.CustomerRequestDto;
import com.softlond.store.services.contracts.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = CustomerApiConstants.CUSTOMER_API_PREFIX)
@AllArgsConstructor
public class CustomerController {

    private final ICustomerService iCustomerService;

    @GetMapping(CustomerApiConstants.LIST)
    private ResponseEntity<List<CustomerRequestDto>> getAll() {
        return new ResponseEntity<List<CustomerRequestDto>>(iCustomerService.getAll(), HttpStatus.OK);
    }

    @GetMapping(CustomerApiConstants.GET_CARD_ID)
    private ResponseEntity<CustomerRequestDto> getByCardId(@RequestParam Integer card) {
        return ResponseEntity.of(iCustomerService.getByCardId(card));
    }

    @GetMapping(CustomerApiConstants.GET_EMAIL)
    private ResponseEntity<List<CustomerRequestDto>> getByEmail(@RequestParam String email) {
        return new ResponseEntity<List<CustomerRequestDto>>(iCustomerService.getByEmail(email), HttpStatus.OK);
    }

/*    @PostMapping("/save")
    private ResponseEntity<CustomerRequestDto> save(@RequestBody CustomerRequestDto customerRequestDto) {
        return new ResponseEntity(iCustomerService.save(customerRequestDto), HttpStatus.CREATED);
    }
*/
    @PutMapping(CustomerApiConstants.UPDATE)
    private ResponseEntity<CustomerRequestDto> update(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.of(iCustomerService.update(customerRequestDto));
    }

    @DeleteMapping(CustomerApiConstants.DELETE)
    private ResponseEntity<Boolean> delete(@RequestParam Long cardId) {
        return new ResponseEntity<Boolean>(iCustomerService.delete(cardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}