package com.codesupreme.anbarapi.dao.satici;

import com.codesupreme.anbarapi.model.satici.Satici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaticiRepository extends JpaRepository<Satici, Long> {

    Satici findSaticiByPhoneNumber(String phoneNumber);
}
