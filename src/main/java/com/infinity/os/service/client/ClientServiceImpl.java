package com.infinity.os.service.client;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.dto.clientdto.ClientUpdateDTO;
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

    //CRUD
    //Create
    @Override
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        Client client = clientMapper.toEntity(dto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponseDTO(savedClient);
    }

    //Read
    @Override
    public ClientResponseDTO searchClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return clientMapper.toResponseDTO(client);
    }

    public Page<ClientResponseDTO> listClient(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toResponseDTO);
    }

    //Update
    @Override
    public ClientResponseDTO updateClient(Long id, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

        // Mantive os IFs separados para permitir atualizar nome, telefone e endereço juntos!
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

    //Delete
    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
    }
}