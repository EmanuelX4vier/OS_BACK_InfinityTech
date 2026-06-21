package com.infinity.os.repository;

import com.infinity.os.entity.Produ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduRepository extends JpaRepository<Produ, String> {
    Page<Produ> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
    @Query("SELECT p FROM Produ p WHERE " +
            "LOWER(p.codigo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.marca) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Produ> globalSearch(@Param("termo") String termo, Pageable pageable);;
}
