package br.com.dimed.sales.service.impl;

import br.com.calazans.constants.SalesOrderStatusEnum;
import br.com.calazans.model.Product;
import br.com.calazans.model.SalesOrder;
import br.com.calazans.model.SalesOrderProduct;
import br.com.calazans.model.Storage;
import br.com.dimed.sales.dto.constants.StorageUpdateOperationEnum;
import br.com.dimed.sales.repository.SalesOrderRepository;
import br.com.dimed.sales.service.ProductService;
import br.com.dimed.sales.service.SalesOrderProductService;
import br.com.dimed.sales.service.SalesOrderService;
import br.com.dimed.sales.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(0);

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesOrderProductService salesOrderProductService;

    @Autowired
    private StorageService storageService;

    @Override
    public List<SalesOrder> listSalesOrders() {
        return salesOrderRepository.findAllByOrderByIssueDateAsc();
    }

    @Override
    public SalesOrder findSalesOrderById(UUID salesOrderId) {
        Optional<SalesOrder> salesOrder = salesOrderRepository.findById(salesOrderId);
        return salesOrder.orElseThrow();
    }

    @Override
    public SalesOrder register(HashMap<UUID, Integer> products) {
        SalesOrder salesOrder = new SalesOrder();
        List<SalesOrderProduct> salesOrderProductList = validateProducts(products);
        try {
            salesOrder.setProductList(salesOrderProductList);
            salesOrder.setIssueDate(LocalDate.now());
            salesOrder.setOrderStatus(SalesOrderStatusEnum.CREATED);
            salesOrder = salesOrderRepository.save(salesOrder);

            salesOrderProductService.registerSalesOrder(salesOrder.getId(), salesOrderProductList);

        } catch (Exception e) {
            salesOrder.setOrderStatus(SalesOrderStatusEnum.ERROR);
            salesOrder = salesOrderRepository.save(salesOrder);

            return salesOrder;
        }
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
                storageService.updateProductStoragePostSale(storedProduct.getId(), entry.getValue(), StorageUpdateOperationEnum.SALE_ORDER);
            }
        }
        return productList;
    }
}
