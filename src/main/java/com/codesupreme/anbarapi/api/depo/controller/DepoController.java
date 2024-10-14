package com.codesupreme.anbarapi.api.depo.controller;

import com.codesupreme.anbarapi.dto.depo.DepoDto;
import com.codesupreme.anbarapi.service.impl.depo.DepoServiceImpl;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/depo")
public class DepoController {

    private final DepoServiceImpl depoServiceImpl;


    public DepoController(DepoServiceImpl depoServiceImpl) {
        this.depoServiceImpl = depoServiceImpl;
    }

    // List
    @GetMapping
    public ResponseEntity<List<DepoDto>> getAllDepos() {
        List<DepoDto> depoDtoList = depoServiceImpl.getAllDepo();
        return ResponseEntity.ok(depoDtoList);
    }

    // Create
    @PostMapping
    public ResponseEntity<DepoDto> createDepo(@RequestBody DepoDto depoDto) {
        DepoDto createdDepo = depoServiceImpl.createDepo(depoDto);
        return ResponseEntity.ok(createdDepo);
    }

    // Id
    @GetMapping("/{depoId}")
    public ResponseEntity<DepoDto> findDepoById(@PathVariable Long depoId) {
        DepoDto depoDto = depoServiceImpl.getDepoById(depoId);
        if (depoDto != null) {
            return ResponseEntity.ok(depoDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Update
    @PutMapping("/{depoId}")
    public ResponseEntity<?> updateDepo(
            @PathVariable Long depoId,
            @RequestBody DepoDto depoDto) {

        try {
            DepoDto updatedDepo = depoServiceImpl.updateDepo(depoId, depoDto);
            if (updatedDepo != null) {
                return ResponseEntity.ok(updatedDepo);
            }
            return ResponseEntity.notFound().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Depo has been modified by another user. Please try again.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete
    @DeleteMapping("/{depoId}")
    public ResponseEntity<String> deleteDepo(@PathVariable Long depoId) {
        boolean deleted = depoServiceImpl.removeById(depoId);
        if (deleted) {
            return ResponseEntity.ok("Depo deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
