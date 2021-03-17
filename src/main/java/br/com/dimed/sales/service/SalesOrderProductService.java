package br.com.dimed.sales.service;

import br.com.calazans.model.SalesOrderProduct;

import java.util.List;
import java.util.UUID;

public interface SalesOrderProductService {

    SalesOrderProduct store(final UUID id, final SalesOrderProduct salesOrderProduct);

    void registerSalesOrder(UUID salesOrderId, List<SalesOrderProduct> salesOrderProductList);

}
