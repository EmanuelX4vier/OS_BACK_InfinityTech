package com.infinity.os.service.produ;

import com.infinity.os.dto.produdto.ProduRequestDTO;
import com.infinity.os.dto.produdto.ProduResponseDTO;
import com.infinity.os.dto.produdto.ProduUpdateDTO;
import com.infinity.os.entity.Produ;
import com.infinity.os.exception.ProduNotFoundException;
import com.infinity.os.mapper.ProduMapper;
import com.infinity.os.repository.ProduRepository;
import com.infinity.os.types.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProduServiceImpl implements ProduService{

    private final ProduRepository produRepository;
    private final ProduMapper produMapper;


    //CRUD
    //Create
    @Override
    public ProduResponseDTO createProdu(ProduRequestDTO dto) {
        Produ produ = produMapper.toEntity(dto);
        Produ produSaver = produRepository.save(produ);
        return produMapper.toResponseDTO(produSaver);
    }

    //Read
    @Override
    public ProduResponseDTO searchProdu(String codigo) {
        Produ produ = produRepository.findById(codigo).orElseThrow(ProduNotFoundException::new);
        return produMapper.toResponseDTO(produ);
    }

    public Page<ProduResponseDTO> listProdu(Pageable pageable){
        return produRepository.findAll(pageable).map(produMapper::toResponseDTO);
    }

    public Page<ProduResponseDTO> searchFor (String termo, Pageable pageable){
        if (termo == null || termo.trim().isEmpty()) {
            return produRepository.findAll(pageable).map(produMapper::toResponseDTO);
        }
        String termoTratado = termo.trim().toUpperCase();
        return produRepository.globalSearch(termoTratado, pageable).map(produMapper::toResponseDTO);
    }

    //Update
    @Override
    public ProduResponseDTO updateProdu(String codigo, ProduUpdateDTO dto) {

        Produ produ = produRepository.findById(codigo).orElseThrow(ProduNotFoundException::new);

        if(dto.getNome() != null){
            produ.setNome(dto.getNome());
        }

        if (dto.getMarca() != null) {
            produ.setMarca(dto.getMarca());
        }

        if (dto.getStatus() != null) {
            produ.setStatus(dto.getStatus());
        }

        if (dto.getQuantidade() != null) {
            produ.setQuantidade(dto.getQuantidade());
            if(dto.getQuantidade() == 0){
                produ.setStatus(Status.INDISPONIVEL);
            } else{
                produ.setStatus(Status.DISPONIVEL);
            }
        }

        if (dto.getValorDeCompra() != null){
            produ.setValorDeCompra(dto.getValorDeCompra());
        }

        if(dto.getValorDeVenda() != null){
            produ.setValorDeVenda(dto.getValorDeVenda());
        }

        Produ produUpdate = produRepository.save(produ);
        return produMapper.toResponseDTO(produUpdate);
    }

    //Delete
    @Override
    public void deleteProdu(String codigo) {
        if(!produRepository.existsById(codigo)){
            throw new ProduNotFoundException();
        }
        produRepository.deleteById(codigo);
    }
}