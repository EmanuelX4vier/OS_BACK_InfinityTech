package com.infinity.os.dto.produdto;

import com.infinity.os.types.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduUpdateDTO {

    private String nome;
    private String marca;
    private Status status;
    private Long quantidade;

}
