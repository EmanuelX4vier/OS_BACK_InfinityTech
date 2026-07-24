package com.infinity.os.controller;

import com.infinity.os.dto.equipdto.EquipRequestDTO;
import com.infinity.os.dto.equipdto.EquipResponseDTO;
import com.infinity.os.dto.equipdto.EquipUpdateDTO;
import com.infinity.os.service.equip.EquipServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equips")
@RequiredArgsConstructor
public class EquipController {

    private final EquipServiceImpl equipService;

    //CRUD
    //Create
    @PostMapping
    public ResponseEntity<EquipResponseDTO> createEquip(@RequestBody @Valid EquipRequestDTO dto) {
        Long clientId = dto.getClientId();
        EquipResponseDTO created = equipService.createEquip(dto, clientId);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{serial}")
    public ResponseEntity<EquipResponseDTO> searchEquip(@PathVariable String serial) {

        EquipResponseDTO equip = equipService.searchEquip(serial);
        return ResponseEntity.ok(equip);
    }

    //Read
    @GetMapping
    public ResponseEntity<Page<EquipResponseDTO>> listEquip(@PageableDefault(size = 20, sort = "serial") Pageable pageable) {
        Page<EquipResponseDTO> pagina = equipService.listEquip(pageable);
        return ResponseEntity.ok(pagina);
    }

    //Update
    @PatchMapping("/{serial}")
    public ResponseEntity<EquipResponseDTO> updateEquip(@PathVariable String serial,
                                        @RequestBody @Valid EquipUpdateDTO dto) {
       EquipResponseDTO update = equipService.updateEquip(serial, dto);
        return ResponseEntity.ok(update);
    }

    //Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{serial}")
    public void deleteEquip(@PathVariable String serial) {
        equipService.deleteEquip(serial);
    }
}