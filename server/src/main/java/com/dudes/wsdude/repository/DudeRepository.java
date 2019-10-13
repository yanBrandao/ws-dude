package com.dudes.wsdude.repository;

import com.dudes.wsdude.domain.Dude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DudeRepository extends JpaRepository<Dude, Long> {

    public Dude findFirstByCPF(String cpf);
}
