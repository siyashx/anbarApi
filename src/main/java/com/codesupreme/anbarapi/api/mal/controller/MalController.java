package com.codesupreme.anbarapi.api.mal.controller;

import com.codesupreme.anbarapi.dto.mal.MalDto;
import com.codesupreme.anbarapi.service.impl.mal.MalServiceImpl;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/mal")
public class MalController {

    private final MalServiceImpl malServiceImpl;

    public MalController(MalServiceImpl malServiceImpl) {
        this.malServiceImpl = malServiceImpl;
    }

    // List
    @GetMapping
    public ResponseEntity<List<MalDto>> getAllMals() {
        List<MalDto> malDtoList = malServiceImpl.getAllMal();
        return ResponseEntity.ok(malDtoList);
    }

    // Create
    @PostMapping
    public ResponseEntity<MalDto> createMal(@RequestBody MalDto malDto) {
        MalDto createdMal = malServiceImpl.createMal(malDto);
        return ResponseEntity.ok(createdMal);
    }

    // Id
    @GetMapping("/{malId}")
    public ResponseEntity<MalDto> findMalById(@PathVariable Long malId) {
        MalDto malDto = malServiceImpl.getMalById(malId);
        if (malDto != null) {
            return ResponseEntity.ok(malDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Update
    @PutMapping("/{malId}")
    public ResponseEntity<?> updateMal(
            @PathVariable Long malId,
            @RequestBody MalDto malDto) {

        try {
            MalDto updatedMal = malServiceImpl.updateMal(malId, malDto);
            if (updatedMal != null) {
                return ResponseEntity.ok(updatedMal);
            }
            return ResponseEntity.notFound().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Mal has been modified by another user. Please try again.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete
    @DeleteMapping("/{malId}")
    public ResponseEntity<String> deleteMal(@PathVariable Long malId) {
        boolean deleted = malServiceImpl.removeById(malId);
        if (deleted) {
            return ResponseEntity.ok("Mal deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
