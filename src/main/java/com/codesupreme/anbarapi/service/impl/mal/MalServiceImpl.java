package com.codesupreme.anbarapi.service.impl.mal;

import com.codesupreme.anbarapi.dao.mal.MalRepository;
import com.codesupreme.anbarapi.dto.mal.MalDto;
import com.codesupreme.anbarapi.model.mal.Mal;
import com.codesupreme.anbarapi.service.inter.mal.MalServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MalServiceImpl implements MalServiceInter {

    private final MalRepository malRepository;
    private final ModelMapper modelMapper;

    public MalServiceImpl(MalRepository malRepository, ModelMapper modelMapper) {
        this.malRepository = malRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MalDto createMal(MalDto malDto) {
        Mal mal = modelMapper.map(malDto, Mal.class);
        mal = malRepository.save(mal);
        return modelMapper.map(mal, MalDto.class);
    }

    @Override
    public List<MalDto> getAllMal() {
        List<Mal> mal = malRepository.findAll();
        return mal.stream()
                .map(mal1 -> modelMapper.map(mal1, MalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long malId) {
        Optional<Mal> malOptional = malRepository.findById(malId);
        if (malOptional.isPresent()) {
            Mal mal = malOptional.get();
            malRepository.delete(mal);
            return true;
        }
        return false;

    }

    @Override
    public MalDto getMalById(Long malId) {
        Optional<Mal> malOptional = malRepository.findById(malId);
        return malOptional.map(mal -> modelMapper.map(mal, MalDto.class)).orElse(null);
    }

    @Override
    public MalDto updateMal(Long malId, MalDto malDto) {
        Optional<Mal> malOptional = malRepository.findById(malId);
        if (malOptional.isPresent()) {
            Mal mal = malOptional.get();

            if (malDto.getDepoId() != null) {
                mal.setDepoId(malDto.getDepoId());
            }

            if (malDto.getName() != null) {
                mal.setName(malDto.getName());
            }

            if (malDto.getQuantity() != null) {
                mal.setQuantity(malDto.getQuantity());
            }

            if (malDto.getBuyPrice() != null) {
                mal.setBuyPrice(malDto.getBuyPrice());
            }

            if (malDto.getSellPrice() != null) {
                mal.setSellPrice(malDto.getSellPrice());
            }

            if (malDto.getIsOld() != null) {
                mal.setIsOld(malDto.getIsOld());
            }

            if (malDto.getIsDept() != null) {
                mal.setIsDept(malDto.getIsDept());
            }

            if (malDto.getCreatedDate() != null) {
                mal.setCreatedDate(malDto.getCreatedDate());
            }

            mal = malRepository.save(mal);
            return modelMapper.map(mal, MalDto.class);
        }
        return null;
    }
}
