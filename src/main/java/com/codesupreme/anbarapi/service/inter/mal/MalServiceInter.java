package com.codesupreme.anbarapi.service.inter.mal;

import com.codesupreme.anbarapi.dto.mal.MalDto;

import java.util.List;

public interface MalServiceInter {

    MalDto createMal(MalDto malDto);

    List<MalDto> getAllMal();

    Boolean removeById(Long malId);

    MalDto getMalById(Long malId);

    MalDto updateMal(Long malId, MalDto malDto);
}
