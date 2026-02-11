package com.gabriel.horta.controller;

import com.gabriel.horta.model.Pessoa;
import com.gabriel.horta.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    // 1.  Listar todos (Pesquisar)
    @GetMapping
    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }

    // 2. Incluir novo cadastro
    @PostMapping
    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
        //  Verifica se o CPF/CNPJ foi enviado
        if (pessoa.getCpfCnpj() != null) {
            //  Limpa tudo que não for número antes de salvar
            String apenasNumeros = pessoa.getCpfCnpj().replaceAll("[^0-9]", "");
            pessoa.setCpfCnpj(apenasNumeros);
        }
        //  Salva no banco com o número limpo
        Pessoa salvo = repository.save(pessoa);
        return ResponseEntity.ok(salvo);
    }

    // 3. Editar Cadastro
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoaNovosDados) {
        return repository.findById(id)
                .map(pessoaExistente -> {
                    pessoaExistente.setNome(pessoaNovosDados.getNome());
                    if (pessoaNovosDados.getCpfCnpj() != null) {
                        pessoaExistente.setCpfCnpj(pessoaNovosDados.getCpfCnpj().replaceAll("[^0-9]", ""));
                    }
                    pessoaExistente.setEndereco(pessoaNovosDados.getEndereco());
                    pessoaExistente.setTelefone(pessoaNovosDados.getTelefone());
                    pessoaExistente.setEmail(pessoaNovosDados.getEmail());
                    pessoaExistente.setEhCliente(pessoaNovosDados.getEhCliente());
                    pessoaExistente.setEhFornecedor(pessoaNovosDados.getEhFornecedor());

                    Pessoa atualizada = repository.save(pessoaExistente);
                    return ResponseEntity.ok(atualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Excluir cadastro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(pessoa -> {
                    repository.delete(pessoa);
                    // Retorna um mapa simples com a mensagem de sucesso
                    return ResponseEntity.ok().body(java.util.Map.of("mensagem", "Pessoa excluída com sucesso!"));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // 5. API DE INTEGRAÇÃO (Busca por documento)
    @GetMapping("/documento/{cpfCnpj}")
    public ResponseEntity<Pessoa> buscarPorDocumento(@PathVariable String cpfCnpj) {
        // 1. Limpa o parâmetro recebido para garantir que a busca seja feita apenas com números
        String documentoLimpo = cpfCnpj.replaceAll("[^0-9]", "");

        // 2. Realiza a busca direta no banco de dados
        return repository.findByCpfCnpj(documentoLimpo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}