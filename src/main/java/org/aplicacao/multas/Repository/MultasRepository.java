package org.aplicacao.multas.Repository;

import org.aplicacao.multas.Entity.ProcessoMulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultasRepository  extends JpaRepository<ProcessoMulta, Long> {

    boolean existsByIdentificador(String identificador); // "
    Optional<ProcessoMulta> findByIdentificador(String identificador);
    void deleteByIdentificador(String identificador); //

}
