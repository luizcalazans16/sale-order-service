package br.com.dimed.sales.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sales_order_product")
@Data
public class SalesOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    @Column(name = "product_quantity")
    private Integer orderedQuantity;

    public SalesOrderProduct(Product product, Integer value) {
        this.product = product;
        this.orderedQuantity = value;
    }

    public SalesOrderProduct() {

    }

}
