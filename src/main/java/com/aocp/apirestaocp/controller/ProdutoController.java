package com.aocp.apirestaocp.controller;

import com.aocp.apirestaocp.model.Produto;
import com.aocp.apirestaocp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    ProdutoRepository RepoProduto;

    @GetMapping("/produtos")
    public ResponseEntity<Page<Produto>> findAll(@PageableDefault(page = 0, size = 40, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Produto> list = RepoProduto.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/produtos/nome/{nome}")
    public ResponseEntity<List<Produto>> findByName(@PathVariable String nome){
       List<Produto> nomes = RepoProduto.findByNomeIgnoreCase(nome);
        return ResponseEntity.ok(nomes);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getProdutosById(@PathVariable("id") long id) {
        Optional<Produto> produtoData = RepoProduto.findById(id);

        if (produtoData.isPresent()) {
            return new ResponseEntity<>(produtoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        try {

          return  ResponseEntity.ok(RepoProduto.save(produto));

        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable("id") long id, @RequestBody Produto produto){
        Optional<Produto> produtoData = RepoProduto.findById(id);

        if (produtoData.isPresent()){
            Produto _produto = produtoData.get();
            _produto.setNome(produto.getNome());
            _produto.setPreco(produto.getPreco());
            _produto.setCodigo(produto.getCodigo());
            _produto.setCategoria(produto.getCategoria());
            _produto.setStatus(produto.getStatus());

            return new ResponseEntity<>(RepoProduto.save(_produto), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id") long id){
        try {
            RepoProduto.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/produtos/ids/{ids}")
    public ResponseEntity<HttpStatus> deleteAllProdutos(@PathVariable List<Produto> produtos){
        try {
            RepoProduto.deleteAllPassingEntites(produtos);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
