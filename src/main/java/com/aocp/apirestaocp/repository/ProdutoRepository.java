package com.aocp.apirestaocp.repository;

import com.aocp.apirestaocp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Serializable> {

    List<Produto> findByNomeIgnoreCase(String nome);


    public void deleteAllPassingEntites(List<Produto> produto);

}
