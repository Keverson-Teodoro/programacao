package proojeto.banco.desafioBanco_de_dados.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proojeto.banco.desafioBanco_de_dados.model.Aluno;

@Repository
public interface UsuarioRepository extends JpaRepository <Aluno, Long> {

    List<Aluno> pessoas = new ArrayList<>();



    

    

    // public void Aluno adicionar(Aluno aluno){
    //     pessoas.add(aluno);

    // }

}
