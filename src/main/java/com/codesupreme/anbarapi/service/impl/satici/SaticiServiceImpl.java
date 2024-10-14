package com.codesupreme.anbarapi.service.impl.satici;

import com.codesupreme.anbarapi.dao.satici.SaticiRepository;
import com.codesupreme.anbarapi.dto.satici.SaticiDto;
import com.codesupreme.anbarapi.model.satici.Satici;
import com.codesupreme.anbarapi.service.inter.satici.SaticiServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaticiServiceImpl implements SaticiServiceInter {

    private final SaticiRepository saticiRepository;
    private final ModelMapper modelMapper;

    public SaticiServiceImpl(SaticiRepository saticiRepository, ModelMapper modelMapper) {
        this.saticiRepository = saticiRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<SaticiDto> createSatici(SaticiDto saticiDto) {
        Satici saticiEntity = modelMapper.map(saticiDto, Satici.class);
        if (isSaticiPhoneNumberTaken(saticiEntity.getPhoneNumber())) {
            return ResponseEntity.badRequest().build();
        }
        saticiEntity = saticiRepository.save(saticiEntity);
        return ResponseEntity.ok(modelMapper.map(saticiEntity, SaticiDto.class));
    }

    @Override
    public List<SaticiDto> getAllSatici() {
        List<Satici> satici = saticiRepository.findAll();
        return satici.stream()
                .map(satici1 -> modelMapper.map(satici1, SaticiDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SaticiDto getSaticiById(Long saticiId) {
        Optional<Satici> saticiOptional = saticiRepository.findById(saticiId);
        return saticiOptional.map(satici -> modelMapper.map(satici, SaticiDto.class)).orElse(null);
    }

    @Override
    public SaticiDto updateSatici(Long saticiId, SaticiDto saticiDto) {
        Optional<Satici> saticiOptional = saticiRepository.findById(saticiId);
        if (saticiOptional.isPresent()) {
            Satici satici = saticiOptional.get();

            if (saticiDto.getDepoId() != null) {
                satici.setDepoId(saticiDto.getDepoId());
            }

            if (saticiDto.getName() != null) {
                satici.setName(saticiDto.getName());
            }
            if (saticiDto.getPhoneNumber() != null) {
                satici.setPhoneNumber(saticiDto.getPhoneNumber());
            }

            if (saticiDto.getPassword() != null) {
                satici.setPassword(saticiDto.getPassword());
            }

            satici = saticiRepository.save(satici);
            return modelMapper.map(satici, SaticiDto.class);
        }
        return null;
    }

    @Override
    public void deleteSatici(Long saticiId) {
        saticiRepository.deleteById(saticiId);
    }

    @Override
    public Boolean isSaticiPhoneNumberTaken(String phoneNumber) {
        return saticiRepository.findSaticiByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public SaticiDto findSaticiByPhoneNumber(String phoneNumber) {
        Satici satici = saticiRepository.findSaticiByPhoneNumber(phoneNumber);
        if (satici != null) {
            return modelMapper.map(satici, SaticiDto.class);
        }
        return null;
    }
}
