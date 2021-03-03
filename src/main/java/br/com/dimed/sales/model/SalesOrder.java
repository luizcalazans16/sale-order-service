package br.com.dimed.sales.model;

import br.com.dimed.sales.dto.constants.SalesOrderStatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<SalesOrderProduct> productList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SalesOrderStatusEnum orderStatus;

}
