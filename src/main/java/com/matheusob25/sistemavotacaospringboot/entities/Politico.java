package com.matheusob25.sistemavotacaospringboot.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_politico")
public class Politico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToMany
    @JoinTable(name = "tb_politico_eleitor", joinColumns = @JoinColumn(name = "id_politico"), inverseJoinColumns = @JoinColumn(name = "id_eleitor"))
    private Set<Eleitor> eleitores = new HashSet<>();

    public Politico() {

    }
    public Politico(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<Eleitor> getEleitores() {
        return eleitores;
    }
    public void adicionaEleitor(Eleitor eleitor) {
        if (eleitor != null && !eleitores.contains(eleitor)) {
            eleitores.add(eleitor);
        }else {
            System.out.println("Erro ao adicionar eleitor, já existe ou valores nulos");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politico politico = (Politico) o;
        return Objects.equals(id, politico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Politico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
