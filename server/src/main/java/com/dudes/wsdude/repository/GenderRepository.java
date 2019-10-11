package com.dudes.wsdude.repository;

import com.dudes.wsdude.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
