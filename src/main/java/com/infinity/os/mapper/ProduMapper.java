package com.infinity.os.mapper;

import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.entity.Produ;
import org.springframework.stereotype.Component;

@Component
public class ProduMapper {

    public Produ toEntity(ProduRequestDTO produDTO) {
        return Produ.builder().codigo(produDTO.getCodigo()).nome(produDTO.getNome()).marca(produDTO.getMarca()).status(produDTO.getStatus()).quantidade(produDTO.getQuantidade()).valorDeCompra(produDTO.getValorDeCompra()).valorDeVenda(produDTO.getValorDeVenda()).build();
    }

    public ProduResponseDTO toResponseDTO(Produ entity){
        ProduResponseDTO produResponseDTO = new ProduResponseDTO(entity.getCodigo(), entity.getNome(), entity.getMarca(), entity.getStatus(), entity.getQuantidade(), entity.getValorDeCompra(), entity.getValorDeVenda());
        return produResponseDTO;
    }
}
