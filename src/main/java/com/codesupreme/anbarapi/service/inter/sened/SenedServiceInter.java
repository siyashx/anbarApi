package com.codesupreme.anbarapi.service.inter.sened;

import com.codesupreme.anbarapi.dto.sened.SenedDto;

import java.util.List;

public interface SenedServiceInter {

    SenedDto createSened(SenedDto senedDto);

    List<SenedDto> getAllSened();

    Boolean removeById(Long senedId);

    SenedDto getSenedById(Long senedId);

    SenedDto updateSened(Long senedId, SenedDto senedDto);
}
