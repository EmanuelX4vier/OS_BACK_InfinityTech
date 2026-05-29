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
            "p.codigo LIKE CONCAT('%', :termo, '%') OR " +
            "p.nome LIKE CONCAT('%', :termo, '%') OR " +
            "p.marca LIKE CONCAT('%', :termo, '%')")
    Page<Produ> globalSearch(@Param("termo") String termo, Pageable pageable);
}
