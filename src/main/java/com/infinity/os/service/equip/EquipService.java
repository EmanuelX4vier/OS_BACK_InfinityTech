package com.infinity.os.service.equip;

import com.infinity.os.dto.equipdto.EquipRequestDTO;
import com.infinity.os.dto.equipdto.EquipResponseDTO;
import com.infinity.os.dto.equipdto.EquipUpdateDTO;

public interface EquipService {
    EquipResponseDTO createEquip(EquipRequestDTO equipRequestDTO, Long clientId);
    EquipResponseDTO searchEquip (String serial);
    EquipResponseDTO updateEquip(String serial, EquipUpdateDTO dto);
    void deleteEquip(String serial);
}
