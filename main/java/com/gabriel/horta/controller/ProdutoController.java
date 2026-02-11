package com.gabriel.horta.controller;

import com.gabriel.horta.model.Produto;
import com.gabriel.horta.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // 1. PESQUISAR (Listar estoque)
    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    // 2. INCLUIR (Cadastrar planta ou insumo)
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    // 3. EDITAR (Atualizar preço, estoque ou cuidados)
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto novosDados) {
        return repository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNomeComum(novosDados.getNomeComum());
                    produtoExistente.setNomeCientifico(novosDados.getNomeCientifico());
                    produtoExistente.setCategoria(novosDados.getCategoria());
                    produtoExistente.setPrecoVenda(novosDados.getPrecoVenda());
                    produtoExistente.setEstoque(novosDados.getEstoque());
                    produtoExistente.setLuminosidade(novosDados.getLuminosidade());
                    produtoExistente.setPetFriendly(novosDados.getPetFriendly());

                    Produto atualizado = repository.save(produtoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return ResponseEntity.ok().body(Map.of("mensagem", "Produto excluído com sucesso!"));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
