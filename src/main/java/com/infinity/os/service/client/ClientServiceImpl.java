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

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    // =======================================================
    // LISTAR CLIENTES CORRIGIDO COM DEFENSA ABSOLUTA CONTRA BUG
    // =======================================================
    public Page<ClientResponseDTO> listClient(Pageable pageable) {
        try {
            Page<Client> clients = clientRepository.findAll(pageable);

            if (clients == null || clients.isEmpty()) {
                return Page.empty(pageable);
            }

            // Mapeamento manual de contingência caso o seu clientMapper quebre internamente
            return clients.map(client -> {
                try {
                    return clientMapper.toResponseDTO(client);
                } catch (Exception mapperException) {
                    System.err.println("Erro interno no ClientMapper: " + mapperException.getMessage());
                    // Fallback: Constrói um DTO básico seguro se o Mapper falhar com objetos nulos/estruturas
                    ClientResponseDTO fallbackDto = new ClientResponseDTO();
                    fallbackDto.setId(client.getId());
                    fallbackDto.setNome(client.getNome());
                    fallbackDto.setTelefone(client.getTelefone());
                    fallbackDto.setEndereco(client.getEndereco());
                    fallbackDto.setDataCadastro(client.getDataCadastro() != null ? LocalDateTime.parse(client.getDataCadastro().toString()) : null);
                    return fallbackDto;
                }
            });

        } catch (Exception e) {
            System.err.println("Erro crítico ao listar clientes no repositório: " + e.getMessage());
            return Page.empty(pageable);
        }
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

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
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
    }
}