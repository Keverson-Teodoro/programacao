package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.repository.MensagemRepository;

public class MensagemService {
    private final MensagemRepository mensagemRepository;

    public MensagemService (MensagemRepository mensagemRepository){
        this.mensagemRepository = mensagemRepository;
    }

    public String getMensage(){
        return mensagemRepository.getMensage();

    
    }
}
