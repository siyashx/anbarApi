package com.codesupreme.anbarapi.dto.satici;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SaticiDto {

    private Long id;
    private Long depoId;
    private String name;
    private String phoneNumber;
    private String password;
}
