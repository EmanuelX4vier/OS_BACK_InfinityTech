package com.infinity.os.service.client;

import com.infinity.os.dto.clientdto.ClientRequestDTO;
import com.infinity.os.dto.clientdto.ClientResponseDTO;
import com.infinity.os.dto.clientdto.ClientUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClientService {

    //CRUD
    ClientResponseDTO createClient(ClientRequestDTO dto);
    ClientResponseDTO searchClient (Long id);
    ClientResponseDTO updateClient (Long id, ClientUpdateDTO dto);
    void deleteClient(Long id);

    //Others
    Page<ClientResponseDTO> listClient(Pageable pageable);
}
