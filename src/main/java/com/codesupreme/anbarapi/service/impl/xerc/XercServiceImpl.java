package com.codesupreme.anbarapi.service.impl.xerc;

import com.codesupreme.anbarapi.dao.xerc.XercRepository;
import com.codesupreme.anbarapi.dto.xerc.XercDto;
import com.codesupreme.anbarapi.model.xerc.Xerc;
import com.codesupreme.anbarapi.service.inter.xerc.XercServiceInter;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XercServiceImpl implements XercServiceInter {

    private final XercRepository xercRepository;
    private final ModelMapper modelMapper;

    public XercServiceImpl(XercRepository xercRepository, ModelMapper modelMapper) {
        this.xercRepository = xercRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public XercDto createXerc(XercDto xercDto) {
        Xerc xerc = modelMapper.map(xercDto, Xerc.class);
        xerc = xercRepository.save(xerc);
        return modelMapper.map(xerc, XercDto.class);
    }

    @Override
    public List<XercDto> getAllXerc() {
        List<Xerc> xerc = xercRepository.findAll();
        return xerc.stream()
                .map(xerc1 -> modelMapper.map(xerc1, XercDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long xercId) {
        Optional<Xerc> xercOptional = xercRepository.findById(xercId);
        if (xercOptional.isPresent()) {
            Xerc xerc = xercOptional.get();
            xercRepository.delete(xerc);
            return true;
        }
        return false;

    }

    @Override
    public XercDto getXercById(Long xercId) {
        Optional<Xerc> xercOptional = xercRepository.findById(xercId);
        return xercOptional.map(xerc -> modelMapper.map(xerc, XercDto.class)).orElse(null);
    }

    @Override
    public XercDto updateXerc(Long xercId, XercDto xercDto) {
        Optional<Xerc> xercOptional = xercRepository.findById(xercId);
        if (xercOptional.isPresent()) {
            Xerc xerc = xercOptional.get();

            if (xercDto.getSaticiId() != null) {
                xerc.setSaticiId(xercDto.getSaticiId());
            }

            if (xercDto.getDepoId() != null) {
                xerc.setDepoId(xercDto.getDepoId());
            }

            if (xercDto.getPrice() != null) {
                xerc.setPrice(xercDto.getPrice());
            }


            if (xercDto.getDescription() != null) {
                xerc.setDescription(xercDto.getDescription());
            }

            if (xercDto.getCreatedDate() != null) {
                xerc.setCreatedDate(xercDto.getCreatedDate());
            }

            xerc = xercRepository.save(xerc);
            return modelMapper.map(xerc, XercDto.class);
        }
        return null;
    }
}
