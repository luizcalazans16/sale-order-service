package br.com.dimed.sales.service.impl;

import br.com.dimed.sales.dto.constants.SalesOrderStatusEnum;
import br.com.dimed.sales.model.Product;
import br.com.dimed.sales.model.SalesOrder;
import br.com.dimed.sales.model.SalesOrderProduct;
import br.com.dimed.sales.model.Storage;
import br.com.dimed.sales.repository.SalesOrderRepository;
import br.com.dimed.sales.service.ProductService;
import br.com.dimed.sales.service.SalesOrderProductService;
import br.com.dimed.sales.service.SalesOrderService;
import br.com.dimed.sales.service.StorageService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesOrderProductService salesOrderProductService;

    @Autowired
    private StorageService storageService;

    @Override
    public SalesOrder findSalesOrderById(UUID salesOrderId) {
        Optional<SalesOrder> salesOrder = salesOrderRepository.findById(salesOrderId);
        return salesOrder.orElseThrow();
    }

    @Override
    public SalesOrder register(HashMap<UUID, Integer> products) {
        SalesOrder salesOrder = new SalesOrder();
        List<SalesOrderProduct> salesOrderProductList = validateProducts(products);

        salesOrder.setProductList(salesOrderProductList);
        salesOrder.setIssueDate(LocalDate.now());
        salesOrder.setOrderStatus(SalesOrderStatusEnum.CREATED);
        salesOrder = salesOrderRepository.save(salesOrder);
        generateReport(List.copyOf(salesOrder.getProductList()), salesOrder.getIssueDate());

        salesOrderProductService.registerSalesOrderId(salesOrder.getId(), salesOrderProductList);

        return salesOrder;
    }

    private List<SalesOrderProduct> validateProducts(HashMap<UUID, Integer> products) {
        List<SalesOrderProduct> productList = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : products.entrySet()) {
            Product storedProduct = productService.findProductById(entry.getKey());
            Storage productStorage = storageService.findStorageByProductId(entry.getKey());

            if (productStorage.getProductQuantity() >= entry.getValue()) {
                SalesOrderProduct orderProduct = salesOrderProductService.store(null,
                        new SalesOrderProduct(storedProduct, entry.getValue()));
                productList.add(orderProduct);
                storageService.updateProductStorage(storedProduct.getId(), entry.getValue());
            }
        }
        return productList;
    }

    private void generateReport(List<SalesOrderProduct> products, LocalDate date) {
        //Exemplo de operação capaz de lançar uma exception
        //products.add(productService.findProductById(products.get(0).getId()));
        products.forEach(System.out::println);
        System.out.println(date);
    }

}
