package br.com.dimed.sales.controller;

import br.com.dimed.sales.dto.SalesOrderDto;
import br.com.dimed.sales.dto.SalesOrderProductDto;
import br.com.dimed.sales.mapper.SalesOrderMapper;
import br.com.dimed.sales.model.SalesOrder;
import br.com.dimed.sales.model.SalesOrderProduct;
import br.com.dimed.sales.service.SalesOrderService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales-order")
@Slf4j
public class SalesOrderController {

    private static final BigDecimal SALE_ORDER_PROFITABLE_PRICE = new BigDecimal(25);

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping
    public List<SalesOrderDto> findAll() {
        log.info("Listando ordens de venda...");

        return salesOrderService.listSalesOrders()
                .stream()
                .map(SalesOrderMapper::map)
                .collect(Collectors.toList());

    }
    @GetMapping("/profitable-price")
    public List<SalesOrderDto> findSalesOrderWithProfitablePrice() {
        log.info("Listando ordens de venda com preço rentável");

        return salesOrderService.listSalesOrders()
                .stream()
                .takeWhile(salesOrder -> salesOrder.getPrice() != null
                        && salesOrder.getPrice()
                        .compareTo(SALE_ORDER_PROFITABLE_PRICE) == 1)
                .map(SalesOrderMapper::map)
                .collect(Collectors.toList());

    }

    @GetMapping("/{salesOrderId}")
    public SalesOrderDto getSalesOrderById(@PathVariable final UUID salesOrderId) {
        log.info("Buscando ordem de venda pelo id: [{}]", salesOrderId);
        return SalesOrderMapper.map(salesOrderService.findSalesOrderById(salesOrderId));
    }

    @PostMapping
    public SalesOrderDto register(@RequestBody @NotNull @NotEmpty HashMap<UUID, Integer> products) {
        log.info("Cadastrando ordem de venda: ordemDeCompra[{}]", products);
        SalesOrder entity = salesOrderService.register(products);
        SalesOrderDto salesOrderDto = SalesOrderMapper.map(entity);

        generateReport(List.copyOf(salesOrderDto.getProductList()), salesOrderDto.getIssueDate());
        return salesOrderDto;
    }

    private void generateReport(List<SalesOrderProductDto> products, LocalDate date) {
        //Exemplo de operação capaz de lançar uma exception
        //products.add(productService.findProductById(products.get(0).getId()));

        products.forEach(System.out::println);
        System.out.println("Ordem de venda gerada. Data da geração: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
