package com.eventec.eventec.repositories;

import com.eventec.eventec.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository {
    Endereco findByCep(String cep);
}