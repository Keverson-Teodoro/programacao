package desafio.itau.Springboot.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
 
// Faça uma classe de serviço de transações que guarde as transações em uma fila e tenha os metodos
// adicionar transações
// limpar transação
// retornar estatistica da data atual



import org.springframework.stereotype.Service;



import desafio.itau.Springboot.model.Transaction;

@Service
public class TransactionService {

    //cria uma fila para guardar as transações
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    //adciciona as transações na fila de transações

    public void addTransaction (Transaction transaction) {
        transactions.add(transaction);
    }

    public void clearTransaction(Transaction transaction){
        transactions.clear();
    }

    // retorna a data atual das estatisticas criadas na classe anterior

    public DoubleSummaryStatistics getStatistics(){

        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}
