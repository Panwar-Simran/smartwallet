package com.smartwallet.smartwallet.security;

import com.smartwallet.smartwallet.dto.TransactionDto;
import com.smartwallet.smartwallet.exception.ResourceNotFoundException;
import com.smartwallet.smartwallet.model.Transaction;
import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.repository.TransactionRepository;
import com.smartwallet.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDto> getSentTransaction(String email) {
         User sender = userRepository.findByEmail(email)
                 .orElseThrow(()->new ResourceNotFoundException("Sender not found!"));

         List<Transaction>transactions=transactionRepository.findBySender(sender);

        List<TransactionDto> dtoList = new ArrayList<>();
        for(Transaction txn:transactions)
        {
            TransactionDto dto=new TransactionDto();
            dto.setSenderEmail(txn.getSender().getEmail());
            dto.setReceiverEmail(txn.getReceiver().getEmail());
            dto.setCreatedAt(txn.getCreatedAt());
            dto.setAmount(txn.getAmount());
            dtoList.add(dto);
            dtoList.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        }
        return dtoList;

    }

    public List<TransactionDto> getReceivedTransaction(String email) {
        User receiver = userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("Receiver not found!"));

        List<Transaction>transactions=transactionRepository.findByReceiver(receiver);

        List<TransactionDto> dtoList = new ArrayList<>();
        for(Transaction txn:transactions)
        {
            TransactionDto dto=new TransactionDto();
            dto.setSenderEmail(txn.getSender().getEmail());
            dto.setReceiverEmail(txn.getReceiver().getEmail());
            dto.setCreatedAt(txn.getCreatedAt());
            dto.setAmount(txn.getAmount());
            dtoList.add(dto);
            dtoList.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        }
        return dtoList;

    }


    public List<TransactionDto> getAllTransactions(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        // Fetch sent and received transactions
        List<Transaction> sent = transactionRepository.findBySender(user);
        List<Transaction> received = transactionRepository.findByReceiver(user);

        List<TransactionDto> dtoList = new ArrayList<>();

        // Add sent transactions
        for (Transaction txn : sent) {
            TransactionDto dto = new TransactionDto();
            dto.setSenderEmail(txn.getSender().getEmail());
            dto.setReceiverEmail(txn.getReceiver().getEmail());
            dto.setAmount(txn.getAmount());
            dto.setCreatedAt(txn.getCreatedAt());
            dtoList.add(dto);
        }

        // Add received transactions
        for (Transaction txn : received) {
            TransactionDto dto = new TransactionDto();
            dto.setSenderEmail(txn.getSender().getEmail());
            dto.setReceiverEmail(txn.getReceiver().getEmail());
            dto.setAmount(txn.getAmount());
            dto.setCreatedAt(txn.getCreatedAt());
            dtoList.add(dto);
        }


        dtoList.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        return dtoList;
    }
}
