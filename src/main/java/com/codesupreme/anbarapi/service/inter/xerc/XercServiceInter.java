package com.codesupreme.anbarapi.service.inter.xerc;

import com.codesupreme.anbarapi.dto.xerc.XercDto;

import java.util.List;

public interface XercServiceInter {

    XercDto createXerc(XercDto xercDto);

    List<XercDto> getAllXerc();

    Boolean removeById(Long xercId);

    XercDto getXercById(Long xercId);

    XercDto updateXerc(Long xercId, XercDto xercDto);
}
