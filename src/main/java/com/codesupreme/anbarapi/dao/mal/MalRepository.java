package com.codesupreme.anbarapi.dao.mal;

import com.codesupreme.anbarapi.model.mal.Mal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MalRepository extends JpaRepository<Mal, Long> {
}
