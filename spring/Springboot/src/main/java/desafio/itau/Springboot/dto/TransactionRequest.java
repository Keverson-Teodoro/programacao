package desafio.itau.Springboot.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// façã uma classe para solicitar as transações
// obs: os campos de valor e de data e hora tem que ser obrigatorios
// obs: o minimo de valores tem que ser 0 (usar anotações como : notnull e min)
// fazer elas retornarem a data e os valores

public class TransactionRequest {

    @NotNull
    @Min(0)
    private Double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public Double getValor(){
        return valor;
    }

    public OffsetDateTime getDataHora(){
        return dataHora;
    }


}
