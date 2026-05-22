package com.infinity.os.dto.clientdto;

import com.infinity.os.dto.equipdto.EquipResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private List<EquipResponseDTO> equips;

    // Alterado o tipo para String para casar perfeitamente com a formatação vinda do Mapper
    private String dataFormatada;
}