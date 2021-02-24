package br.com.dimed.sales.repository;

import br.com.dimed.sales.model.SalesOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesOrderProductRepository extends JpaRepository<SalesOrderProduct, UUID> {
}
