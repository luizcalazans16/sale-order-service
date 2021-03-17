package br.com.dimed.sales.service.impl;

import br.com.calazans.model.SalesOrder;
import br.com.calazans.model.SalesOrderProduct;
import br.com.dimed.sales.repository.SalesOrderProductRepository;
import br.com.dimed.sales.service.SalesOrderProductService;
import br.com.dimed.sales.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalesOrderProductServiceImpl implements SalesOrderProductService {

    @Autowired
    private SalesOrderProductRepository salesOrderProductRepository;

    @Autowired
    private SalesOrderService salesOrderService;

    @Override
    public SalesOrderProduct store(UUID id, SalesOrderProduct salesOrderProduct) {
        var entity = new SalesOrderProduct();
        entity.setId(id);
        entity = salesOrderProductRepository.save(salesOrderProduct);
        return entity;
    }

    @Override
    public void registerSalesOrder(final UUID salesOrderId, final List<SalesOrderProduct> salesOrderProductList) {
        SalesOrder storedSaleOrder = salesOrderService.findSalesOrderById(salesOrderId);
        for (SalesOrderProduct salesOrderProduct : salesOrderProductList) {
            salesOrderProduct.setSalesOrder(storedSaleOrder);
            salesOrderProductRepository.save(salesOrderProduct);
        }
    }
}
