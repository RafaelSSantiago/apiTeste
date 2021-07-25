package com.aocp.apirestaocp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Double preco;

    private String codigo;

    @Enumerated(value = EnumType.STRING)
    private Categoria categoria;

    @Enumerated(value = EnumType.STRING)
    private Status status ;

    public Produto(){}

    public Produto(String nome, Double preco, String codigo, Categoria categoria, Status status){
        this.nome = nome;
        this.preco = '$' + preco;
        this.codigo = codigo;
        this.categoria = categoria;
        this.status = status;
    }


}
