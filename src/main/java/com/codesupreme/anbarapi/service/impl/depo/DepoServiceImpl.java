package com.codesupreme.anbarapi.service.impl.depo;

import com.codesupreme.anbarapi.dao.depo.DepoRepository;
import com.codesupreme.anbarapi.dto.depo.DepoDto;
import com.codesupreme.anbarapi.model.depo.Depo;
import com.codesupreme.anbarapi.service.inter.depo.DepoServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepoServiceImpl implements DepoServiceInter {

    private final DepoRepository depoRepository;
    private final ModelMapper modelMapper;

    public DepoServiceImpl(DepoRepository depoRepository, ModelMapper modelMapper) {
        this.depoRepository = depoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepoDto createDepo(DepoDto depoDto) {
        Depo depo = modelMapper.map(depoDto, Depo.class);
        depo = depoRepository.save(depo);
        return modelMapper.map(depo, DepoDto.class);
    }

    @Override
    public List<DepoDto> getAllDepo() {
        List<Depo> depo = depoRepository.findAll();
        return depo.stream()
                .map(depo1 -> modelMapper.map(depo1, DepoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long depoId) {
        Optional<Depo> depoOptional = depoRepository.findById(depoId);
        if (depoOptional.isPresent()) {
            Depo depo = depoOptional.get();
            depoRepository.delete(depo);
            return true;
        }
        return false;

    }

    @Override
    public DepoDto getDepoById(Long depoId) {
        Optional<Depo> depoOptional = depoRepository.findById(depoId);
        return depoOptional.map(depo -> modelMapper.map(depo, DepoDto.class)).orElse(null);
    }

    @Override
    public DepoDto updateDepo(Long depoId, DepoDto depoDto) {
        Optional<Depo> depoOptional = depoRepository.findById(depoId);
        if (depoOptional.isPresent()) {
            Depo depo = depoOptional.get();

            if (depoDto.getName() != null) {
                depo.setName(depoDto.getName());
            }

            depo = depoRepository.save(depo);
            return modelMapper.map(depo, DepoDto.class);
        }
        return null;
    }
}
