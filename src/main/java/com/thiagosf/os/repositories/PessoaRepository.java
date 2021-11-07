package com.thiagosf.os.repositories;

import java.util.Optional;

import com.thiagosf.os.domain.Pessoa;
import com.thiagosf.os.domain.Tecnico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Tecnico, Integer> {

  @Query(value = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf=:cpf")
  Optional<Pessoa> findByCpf(@Param("cpf") String cpf);

}
