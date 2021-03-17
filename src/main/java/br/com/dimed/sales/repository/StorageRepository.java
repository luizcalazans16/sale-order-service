package br.com.dimed.sales.repository;

import br.com.calazans.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {

    Optional<Storage> findStorageByProductId(final UUID productId);

}
