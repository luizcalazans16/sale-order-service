package br.com.dimed.sales.service;

import br.com.dimed.sales.dto.SalesOrderDto;
import br.com.dimed.sales.model.SalesOrder;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface SalesOrderService {

    SalesOrder findSalesOrderById(UUID salesOrderId);

    SalesOrder register(HashMap<UUID, Integer> products);
}
