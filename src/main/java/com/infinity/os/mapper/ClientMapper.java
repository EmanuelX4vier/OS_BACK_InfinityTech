package com.infinity.os.mapper;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.entity.Client;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientMapper {

    public Client toEntity (ClientRequestDTO dto){
        return Client.builder()
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();
    }

    public ClientResponseDTO toResponseDTO(Client entity){
        // Retornamos a lista vazia ou nula para os equipamentos aqui,
        // evitando quebras de dependência circular no carregamento dos Bean do Spring.
        return new ClientResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEndereco(),
                List.of(), // Limpa a dependência que gerava o erro interno
                entity.getDataCadastro()
        );
    }
}