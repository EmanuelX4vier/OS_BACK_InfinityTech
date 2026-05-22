package com.infinity.os.mapper;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.dto.equipdto.EquipResponseDTO;
import com.infinity.os.entity.Client;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final EquipMapper equipMapper;

    // Declaração do formatador padrão que estava faltando no escopo
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Client toEntity (ClientRequestDTO dto){
        return Client.builder()
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();
    }

    public ClientResponseDTO toResponseDTO(Client entity){
        List<EquipResponseDTO> equipsDTO = (entity.getEquips() == null)
                ? List.of()
                : entity.getEquips().stream().map(equipMapper::toResponseDTO).toList();

        // Fazemos a conversão segura do LocalDateTime para String formatada
        String dataFormatada = (entity.getDataCadastro() != null)
                ? entity.getDataCadastro().format(FORMATTER)
                : null;

        return new ClientResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEndereco(),
                equipsDTO,
                dataFormatada // Passando o valor da String tratada
        );
    }
}