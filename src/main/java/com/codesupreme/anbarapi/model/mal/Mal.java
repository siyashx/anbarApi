package com.codesupreme.anbarapi.model.mal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Mal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long depoId;
    private String name;
    private Long quantity;
    private Double buyPrice;
    private Double sellPrice;
    private Boolean isOld;
    private Date createdDate;


}
