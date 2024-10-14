package com.codesupreme.anbarapi.dto.depo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DepoDto {

    private Long id;
    private String name;
}
