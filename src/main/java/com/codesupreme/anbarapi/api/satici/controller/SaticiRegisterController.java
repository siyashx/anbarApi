package com.codesupreme.anbarapi.api.satici.controller;

import com.codesupreme.anbarapi.dto.satici.SaticiDto;
import com.codesupreme.anbarapi.service.impl.satici.SaticiServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/create/satici")
public class SaticiRegisterController {

    private final SaticiServiceImpl saticiServiceImpl;

    public SaticiRegisterController(SaticiServiceImpl saticiServiceImpl) {
        this.saticiServiceImpl = saticiServiceImpl;
    }

    // Create
    @PostMapping
    public ResponseEntity<?> createSatici(@RequestBody SaticiDto saticiDto) {
        return saticiServiceImpl.createSatici(saticiDto);
    }
}
