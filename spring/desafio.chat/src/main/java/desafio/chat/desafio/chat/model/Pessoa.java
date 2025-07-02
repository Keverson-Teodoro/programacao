package desafio.chat.desafio.chat.model;

import api.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Pessoa {

    @NotNull
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column (name = "cidade")
    private String cidade;

    
    
    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    public long getId(){
        return id;
    }

    public String getCidaded(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }



    @Override
    public String toString(){
        return "{ 'Nome': " + nome + ", " + "'Idade' :" + idade + ", "+ " 'Id': " + id + "}" + ",";
    }

    public Pessoa(String nome, int idade, String cidade){
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;

    }
 
}
