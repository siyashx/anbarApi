package com.codesupreme.anbarapi.service.impl.sened;

import com.codesupreme.anbarapi.dao.sened.SenedRepository;
import com.codesupreme.anbarapi.dto.sened.SenedDto;
import com.codesupreme.anbarapi.model.sened.Sened;
import com.codesupreme.anbarapi.service.inter.sened.SenedServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SenedServiceImpl implements SenedServiceInter {

    private final SenedRepository senedRepository;
    private final ModelMapper modelMapper;

    public SenedServiceImpl(SenedRepository senedRepository, ModelMapper modelMapper) {
        this.senedRepository = senedRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SenedDto createSened(SenedDto senedDto) {
        Sened sened = modelMapper.map(senedDto, Sened.class);
        sened = senedRepository.save(sened);
        return modelMapper.map(sened, SenedDto.class);
    }

    @Override
    public List<SenedDto> getAllSened() {
        List<Sened> sened = senedRepository.findAll();
        return sened.stream()
                .map(sened1 -> modelMapper.map(sened1, SenedDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long senedId) {
        Optional<Sened> senedOptional = senedRepository.findById(senedId);
        if (senedOptional.isPresent()) {
            Sened sened = senedOptional.get();
            senedRepository.delete(sened);
            return true;
        }
        return false;

    }

    @Override
    public SenedDto getSenedById(Long senedId) {
        Optional<Sened> senedOptional = senedRepository.findById(senedId);
        return senedOptional.map(sened -> modelMapper.map(sened, SenedDto.class)).orElse(null);
    }

    @Override
    public SenedDto updateSened(Long senedId, SenedDto senedDto) {
        Optional<Sened> senedOptional = senedRepository.findById(senedId);
        if (senedOptional.isPresent()) {
            Sened sened = senedOptional.get();

            if (senedDto.getDepoId() != null) {
                sened.setDepoId(senedDto.getDepoId());
            }

            if (senedDto.getMalId() != null) {
                sened.setMalId(senedDto.getMalId());
            }

            if (senedDto.getSaticiId() != null) {
                sened.setSaticiId(senedDto.getSaticiId());
            }

            if (senedDto.getType() != null) {
                sened.setType(senedDto.getType());
            }

            if (senedDto.getPaymentType() != null) {
                sened.setPaymentType(senedDto.getPaymentType());
            }

            if (senedDto.getMalCount() != null) {
                sened.setMalCount(senedDto.getMalCount());
            }

            if (senedDto.getPrice() != null) {
                sened.setPrice(senedDto.getPrice());
            }


            if (senedDto.getCreatedDate() != null) {
                sened.setCreatedDate(senedDto.getCreatedDate());
            }

            sened = senedRepository.save(sened);
            return modelMapper.map(sened, SenedDto.class);
        }
        return null;
    }
}
