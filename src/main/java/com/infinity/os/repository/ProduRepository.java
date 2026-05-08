package com.infinity.os.repository;

import com.infinity.os.entity.Produ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduRepository extends JpaRepository<Produ, String> {
    Page<Produ> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
