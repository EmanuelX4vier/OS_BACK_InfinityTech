package com.infinity.os.service.produ;

import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.dto.produdto.ProduUpdateDTO;

public interface ProduService {
    ProduResponseDTO createProdu(ProduRequestDTO dto);
    ProduResponseDTO searchProdu(String codigo);
    ProduResponseDTO updateProdu(String codigo, ProduUpdateDTO dto);
    void deleteProdu(String codigo);
}
