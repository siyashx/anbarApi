package com.codesupreme.anbarapi.service.inter.satici;

import com.codesupreme.anbarapi.dto.satici.SaticiDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaticiServiceInter {

    List<SaticiDto> getAllSatici();
    SaticiDto getSaticiById(Long saticiId);
    ResponseEntity<SaticiDto> createSatici(SaticiDto saticiDto);
    SaticiDto updateSatici(Long saticiId, SaticiDto saticiDto);
    void deleteSatici(Long saticiId);
    Boolean isSaticiPhoneNumberTaken(String phoneNumber);
    SaticiDto findSaticiByPhoneNumber(String phoneNumber);
}
