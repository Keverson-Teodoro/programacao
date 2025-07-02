package desafio.itau.Springboot;

import org.springframework.web.bind.annotation.RestController;

import desafio.itau.Springboot.dto.TransactionRequest;
import desafio.itau.Springboot.model.Transaction;
import desafio.itau.Springboot.service.TransactionService;
import jakarta.validation.Valid;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final service.TransactionService transactionService;

    public TransactionController(TransactionService transactionService, service.TransactionService transactionService){

        this.transactionService = transactionService;
    }
    
    @PostMapping
    public ResponseEntity<Void> createTransaction (@Valid @RequestBody TransactionRequest request) {
        if(request.getDataHora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.unprocessableEntity().build();
        }
        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));

        
        
        return entity;
    }
    
    
}
