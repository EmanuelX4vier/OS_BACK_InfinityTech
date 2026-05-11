package com.infinity.os.dto.produdto;

import com.infinity.os.types.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduRequestDTO {

    @NotBlank(message = "Código de barras do produto é obrigatório!")
    private String codigo;

    @NotBlank(message = "Nome do produto é obrigatório!")
    private String nome;

    @NotBlank(message = "Marca do produto é obrigatório!")
    private String marca;

    @NotNull(message = "Status do produto é obrigatório!")
    private Status status;

    @NotNull(message = "A quantidade é óbrigatória!")
    private Long quantidade;

    @NotNull(message = "O valor de compra é obrigatório!")
    private BigDecimal valorDeCompra;

    @NotNull(message = "O valor de venda é obrigatório!")
    private BigDecimal valorDeVenda;

}
