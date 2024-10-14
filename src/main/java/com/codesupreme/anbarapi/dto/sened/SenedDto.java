package com.codesupreme.anbarapi.dto.sened;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SenedDto {

    private Long id;
    private Long depoId;
    private Long malId;
    private Long saticiId;
    private String type;
    private String paymentType;
    private Integer malCount;
    private Double price;
    private Date createdDate;
}
