package desafio.chat.desafio.chat.repository;

import java.time.Period;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import desafio.chat.desafio.chat.model.Pessoa;


@Repository
public interface UsuarioRepository extends JpaRepository<Pessoa, Long> {

    

    
}
