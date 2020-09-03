/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Meyrielle
 */
public class Profissional {
    private String nome;
    private int idade;
    private String profissao;
    private String competencia;
    
    public Profissional(){
        this.nome = "default";
        this.idade = 0;
        this.profissao = "default";
        this.competencia = "default";
    }
    
    public Profissional(String nome, int idade, String profissao, String competencia){
        this.nome = nome;
        this.idade = idade;
        this.profissao = profissao;
        this.competencia = competencia;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    public void setProfissao(String profissao){
        this.profissao = profissao;
    }
    
    public void setCompetencia(String competencia){
        this.competencia = competencia;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getIdade(){
        return this.idade;
    }
    
    public String getProfissao(){
        return this.profissao;
    }
    
    public String getCompetencia()
    {
        return this.competencia;
    }
}
