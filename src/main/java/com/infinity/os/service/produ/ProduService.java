package com.infinity.os.service.produ;

import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.dto.produdto.ProduUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProduService {
    ProduResponseDTO createProdu(ProduRequestDTO dto);
    ProduResponseDTO searchProdu(String codigo);
    Page<ProduResponseDTO> listProdu(Pageable pageable);
    ProduResponseDTO updateProdu(String codigo, ProduUpdateDTO dto);
    void deleteProdu(String codigo);
}
