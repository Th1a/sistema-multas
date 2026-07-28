package org.aplicacao.multas.Repository;

import org.aplicacao.multas.Entity.StatusProcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends  JpaRepository<StatusProcesso,Long> {

    public Optional<StatusProcesso> findByIdentificador(String identificador);



}
