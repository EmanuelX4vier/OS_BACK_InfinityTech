package com.infinity.os.controller;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.dto.clientdto.ClientUpdateDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.service.client.ClientService;
import com.infinity.os.service.client.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Valid ClientRequestDTO dto) {
        ClientResponseDTO created = clientService.createClient(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> searchClient(@PathVariable Long clientId) {
        ClientResponseDTO client = clientService.searchClient(clientId);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> listClient(@PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<ClientResponseDTO> pagina = clientService.listClient(pageable);
        return ResponseEntity.ok(pagina);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable Long clientId,
            @RequestBody @Valid ClientUpdateDTO dto
    ) {
        ClientResponseDTO updated = clientService.updateClient(clientId, dto);
        return ResponseEntity.ok(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }
}