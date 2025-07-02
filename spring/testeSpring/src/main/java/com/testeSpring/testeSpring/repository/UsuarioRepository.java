package com.testeSpring.testeSpring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.testeSpring.testeSpring.model.Usuario;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

        
}
    

