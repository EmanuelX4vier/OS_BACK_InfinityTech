package com.infinity.os.service.client;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.dto.clientdto.ClientUpdateDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.entity.Client;
import com.infinity.os.exception.ClientNotFoundException;
import com.infinity.os.mapper.ClientMapper;
import com.infinity.os.repository.ClientRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponseDTO(savedClient);
    }

    @Override
    public ClientResponseDTO searchClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientMapper.toResponseDTO(client);
    }

    // ==========================================
    // LISTAR CLIENTES (PROTEGIDO CONTRA ERRO 500)
    // ==========================================
    public Page<ClientResponseDTO> listClient(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        // Se o resultado do banco for nulo ou vier completamente vazio,
        // retorna um Page vazio estruturado para evitar erros na conversão de dados da API.
        if (clients == null || clients.isEmpty()) {
            return Page.empty(pageable);
        }

        return clients.map(clientMapper::toResponseDTO);
    }

    // ==========================================
    // ATUALIZAR CLIENTE (CORRIGIDO PARA MÚLTIPLOS CAMPOS)
    // ==========================================
    @Override
    public ClientResponseDTO updateClient(Long id, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

        // Validações individuais para permitir atualizar múltiplos atributos simultaneamente
        if (dto.getNome() != null) {
            client.setNome(dto.getNome());
        }
        if (dto.getTelefone() != null) {
            client.setTelefone(dto.getTelefone());
        }
        if (dto.getEndereco() != null) {
            client.setEndereco(dto.getEndereco());
        }

        Client updateClient = clientRepository.save(client);
        return clientMapper.toResponseDTO(updateClient);
    }

    @Override
    public void deleteClient(Long id) {
        // Verifica se o client existe.
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
    }
}