package br.com.dimed.sales.service;

import br.com.calazans.model.SalesOrder;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface SalesOrderService {

    SalesOrder findSalesOrderById(UUID salesOrderId);

    SalesOrder register(HashMap<UUID, Integer> products);

    List<SalesOrder> listSalesOrders();
}
