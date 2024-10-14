package com.codesupreme.anbarapi.dto.mal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MalDto {

    private Long id;
    private Long depoId;
    private String name;
    private Long quantity;
    private Double buyPrice;
    private Double sellPrice;
    private Boolean isOld;
    private Boolean isDept;
    private Date createdDate;

}
