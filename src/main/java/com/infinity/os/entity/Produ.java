package com.infinity.os.entity;

import com.infinity.os.types.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produ {

    @Id
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "quantidade", nullable = true)
    private Long quantidade;

}
