package com.softlond.store.constants;

public class PurchaseApiConstants {

    /** Constantes CRUD genéricas **/
    public static final String CREATE = CrudApiConstants.CREATE;
    public static final String LIST = CrudApiConstants.LIST;
    public static final String UPDATE = CrudApiConstants.UPDATE;
    public static final String DELETE = CrudApiConstants.DELETE;

    /** Purchase controller api **/
    public static final String PURCHASE_API_PREFIX = "/purchase";

    /**
     * Obtener compras segun su id (id = PRIMARY KEY)
     */
    public static final String GET_ID = "/get-id";

    /**
     * Obtener compras por el codigo de la compra realizada
     */
    public static final String GET_CODE_PURCHASE = "/get-code-purchase";

    /**
     * Obtener compras por clientes segun su cedula
     */
    public static final String GET_CUSTOMER_BY_CARD_ID = "/get-customer-card-id";

    /**
     * Obtener compras por un rango de fechas especificados
     */
    public static final String PURCHASE_GET_DATE_BY_RANGE = "/get-date";

    /**
     * Obtener compras realizadas por clientes según su número de cédula
     * y
     * dentro de un rango de fechas especificado
     */
    public static final String GET_CUSTOMER_BY_CARD_ID_AND_DATE_BY_RANGE = "/get-customer-and-date";

}