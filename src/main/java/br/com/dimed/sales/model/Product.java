package br.com.dimed.sales.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "default_price")
    private BigDecimal defaultPrice;

    @Column(name = "description")
    private String description;

}
