package com.smartwallet.smartwallet.controller;

import com.smartwallet.smartwallet.dto.TransactionDto;
import com.smartwallet.smartwallet.repository.UserRepository;
import com.smartwallet.smartwallet.security.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Sent Transactions
    @GetMapping("/sent")
    ResponseEntity<List<TransactionDto>>getSentTransaction(Principal principal)
    {
        List<TransactionDto> transactions =transactionService.getSentTransaction(principal.getName());
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

    // Received Transactions
    @GetMapping("/received")
    ResponseEntity<List<TransactionDto>>getReceivedTransaction(Principal principal)
    {
        List<TransactionDto> transactions =transactionService.getReceivedTransaction(principal.getName());
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

   //all transactions
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(Principal principal) {
        List<TransactionDto> list = transactionService.getAllTransactions(principal.getName());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
