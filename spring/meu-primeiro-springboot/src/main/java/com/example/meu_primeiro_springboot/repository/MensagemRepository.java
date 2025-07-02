package com.example.meu_primeiro_springboot.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {
    public String getMensage(){
        
        return "Olá repositorio";
    }
}
