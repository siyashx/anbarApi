package com.codesupreme.anbarapi.model.satici;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Satici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long depoId;
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String password;
}
