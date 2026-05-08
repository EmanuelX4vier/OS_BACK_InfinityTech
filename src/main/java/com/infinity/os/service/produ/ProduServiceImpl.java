package com.infinity.os.service.produ;

import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.dto.produdto.ProduUpdateDTO;
import com.infinity.os.entity.Produ;
import com.infinity.os.exception.ProduNotFoundException;
import com.infinity.os.mapper.ProduMapper;
import com.infinity.os.repository.ProduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProduServiceImpl implements ProduService{

    private final ProduRepository produRepository;
    private final ProduMapper produMapper;

    @Override
    public ProduResponseDTO createProdu(ProduRequestDTO dto) {
        Produ produ = produMapper.toEntity(dto);
        Produ produSaver = produRepository.save(produ);
        return produMapper.toResponseDTO(produSaver);
    }

    @Override
    public ProduResponseDTO searchProdu(String codigo) {
        Produ produ = produRepository.findById(codigo).orElseThrow(ProduNotFoundException::new);
        return produMapper.toResponseDTO(produ);
    }

    public Page<ProduResponseDTO> listProdu(Pageable pageable){
        return produRepository.findAll(pageable).map(produMapper::toResponseDTO);

    }

    @Override
    public ProduResponseDTO updateProdu(String codigo, ProduUpdateDTO dto) {
        Produ produ = produRepository.findById(codigo).orElseThrow(ProduNotFoundException::new);
        if(dto.getNome() != null){
            produ.setNome(dto.getNome());
        } else if (dto.getMarca() != null) {
            produ.setMarca(dto.getMarca());
        } else if (dto.getStatus() != null) {
            produ.setStatus(dto.getStatus());
        } else if (dto.getQuantidade() != null) {
            produ.setQuantidade(dto.getQuantidade());
        }
        Produ produUpdate = produRepository.save(produ);
        return produMapper.toResponseDTO(produUpdate);
    }

    @Override
    public void deleteProdu(String codigo) {
        if(!produRepository.existsById(codigo)){
            throw new ProduNotFoundException();
        }
        produRepository.deleteById(codigo);
    }
}
