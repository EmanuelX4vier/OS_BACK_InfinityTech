package com.infinity.os.dto.clientdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infinity.os.dto.equipdto.EquipResponseDTO;
import lombok.*;
import java.time.LocalDateTime;
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

    //ESSA ANOTAÇÃO MATA O ERRO 500 NA LISTAGEM E NA BUSCA POR ID
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}