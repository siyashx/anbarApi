package com.codesupreme.anbarapi.service.inter.depo;

import com.codesupreme.anbarapi.dto.depo.DepoDto;

import java.util.List;

public interface DepoServiceInter {

    DepoDto createDepo(DepoDto depoDto);

    List<DepoDto> getAllDepo();

    Boolean removeById(Long depoId);

    DepoDto getDepoById(Long depoId);

    DepoDto updateDepo(Long depoId, DepoDto depoDto);

}
