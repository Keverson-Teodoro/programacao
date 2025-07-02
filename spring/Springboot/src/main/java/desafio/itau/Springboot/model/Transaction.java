package desafio.itau.Springboot.model;

import java.time.OffsetDateTime;

// crie uma representação das trasações e retorne os valores


public class Transaction {
    private double valor;
    private OffsetDateTime dataHora;

    public Transaction(double valor, OffsetDateTime dataHora){
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor(){
        return valor;
    }

    public OffsetDateTime getDataHora(){
        return dataHora;
    }



}
