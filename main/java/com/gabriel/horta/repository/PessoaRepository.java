package com.gabriel.horta.repository;

import com.gabriel.horta.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Método para cumprir o requisito de busca por CPF/CNPJ
    Optional<Pessoa> findByCpfCnpj(String cpfCnpj);
}