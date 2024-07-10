package com.matheusob25.sistemavotacaospringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "tb_eleitor")
public class Eleitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @JsonIgnore
    @ManyToMany(mappedBy = "eleitores")
    private Set<Politico> politicos = new HashSet<>();

    public Eleitor(){

    }
    public Eleitor(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Politico> getPoliticos() {
        return politicos;
    }
    public void votarPolitico(Politico politico) {
        if(politico != null && politicos.size() == 0){
            politicos.add(politico);
            politico.adicionaEleitor(this);
        } else if (politico != null && politicos.size() > 0) {
            politicos.removeAll(getPoliticos());
            politicos.add(politico);
            politico.adicionaEleitor(this);
        }else{
            System.out.println("Politico não existe na base de dados");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eleitor eleitor = (Eleitor) o;
        return Objects.equals(id, eleitor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Eleitor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha ;
    }
}
