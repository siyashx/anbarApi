package com.codesupreme.anbarapi.dto.xerc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class XercDto {

    private Long id;
    private Long saticiId;
    private Long depoId;
    private Double price;
    private String description;
    private Date createdDate;
}
