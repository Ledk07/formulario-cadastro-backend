package com.api.formulario_cadastrov2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;




public class FormularioCadastroDto {
    @JsonProperty("nome")
    @NotBlank
    private String nome;

    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;

    @JsonProperty("dataDeNascimento")
    @NotNull
    @Past
    private LocalDate dataDeNascimento;

    @JsonProperty("senha")
    @NotBlank
    private String senha;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
     
    
    



}
