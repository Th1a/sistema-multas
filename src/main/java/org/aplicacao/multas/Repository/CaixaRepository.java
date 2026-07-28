package org.aplicacao.multas.Repository;

import org.aplicacao.multas.Entity.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {

    Optional<Caixa> findByNome(String nome);
    boolean existsByNome (String nome);


}
