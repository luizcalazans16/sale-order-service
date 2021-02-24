package br.com.dimed.sales.controller;

import br.com.dimed.sales.dto.SalesOrderDto;
import br.com.dimed.sales.mapper.SalesOrderMapper;
import br.com.dimed.sales.model.SalesOrder;
import br.com.dimed.sales.service.SalesOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales-order")
@Slf4j
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping("/{salesOrderId}")
    public SalesOrderDto getSalesOrderById(@PathVariable final UUID salesOrderId) {
        log.info("Buscando ordem de compra pelo id: [{}]", salesOrderId);
        return SalesOrderMapper.map(salesOrderService.findSalesOrderById(salesOrderId));
    }

    @PostMapping
    public SalesOrderDto register(@RequestBody @NotNull @NotEmpty HashMap<UUID, Integer> products) {
        log.info("Cadastrando ordem de compra: ordemDeCompra[{}]", products);
        SalesOrder entity;
        entity = salesOrderService.register(products);
        return SalesOrderMapper.map(entity);
    }
}
