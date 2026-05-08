package com.infinity.os.controller;

import com.infinity.os.dto.equipdto.EquipResponseDTO;
import com.infinity.os.dto.equipdto.EquipUpdateDTO;
import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.dto.produdto.ProduUpdateDTO;
import com.infinity.os.service.produ.ProduService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProduController {

    private final ProduService produService;

    @PostMapping
    public ResponseEntity<ProduResponseDTO> createProdu(@RequestBody @Valid ProduRequestDTO dto){
        ProduResponseDTO created = produService.createProdu(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProduResponseDTO> searchProdu(@PathVariable String codigo){
        ProduResponseDTO produ = produService.searchProdu(codigo);
        return ResponseEntity.ok(produ);
    }

    @PatchMapping("/{codigo}")
    public ResponseEntity<ProduResponseDTO> updateEquip(@PathVariable String codigo,
                                                        @RequestBody @Valid ProduUpdateDTO dto) {
        ProduResponseDTO update = produService.updateProdu(codigo, dto);
        return ResponseEntity.ok(update);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{codigo}")
    public void deleteProdu(@PathVariable String codigo) {
        produService.deleteProdu(codigo);
    }



}
