package org.aplicacao.multas.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "caixa")
public class Caixa {

    public Caixa(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Caixa() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;


    }


