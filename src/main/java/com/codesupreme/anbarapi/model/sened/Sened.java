package com.codesupreme.anbarapi.model.sened;

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
public class Sened {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long depoId;
    private Long malId;
    private Long saticiId;
    private String type;
    private String paymentType;
    private Date createdDate;

}
