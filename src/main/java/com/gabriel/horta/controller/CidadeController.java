package com.gabriel.horta.controller;

import com.gabriel.horta.model.Cidade;
import com.gabriel.horta.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

        @Autowired
        private CidadeRepository repository;

        // 1. Pesquisar ( Listar Todas)
        @GetMapping
        public List<Cidade> listarTodas() {
            return repository.findAll();
        }

        //2. Incluir
        @PostMapping
        public Cidade salvar(@RequestBody Cidade cidade) {
            return repository.save(cidade);
        }

        //3. Editar
        @PutMapping("/{id}")
        public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidadeNovosDados){
            return repository.findById(id)
                    .map(cidadeExistente ->{
                        cidadeExistente.setNome(cidadeNovosDados.getNome());
                        cidadeExistente.setUf(cidadeNovosDados.getUf());
                        Cidade atualizada = repository.save(cidadeExistente);
                        return ResponseEntity.ok(atualizada);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        // 4. Excluir
        @DeleteMapping("/{id}")
        public ResponseEntity<?> excluir(@PathVariable Long id){
            return repository.findById(id)
                    .map(cidade -> {
                        repository.delete(cidade);
                        return ResponseEntity.ok().body(Map.of("mensagem","Cidade Excluída com sucesso!"));
                    })
                    .orElse(ResponseEntity.notFound().build());
        }


}
