package com.smartwallet.smartwallet.service;

import com.smartwallet.smartwallet.dto.AddMoneyDto;
import com.smartwallet.smartwallet.dto.TransferMoneyDto;
import com.smartwallet.smartwallet.dto.WalletBalanceDto;
import com.smartwallet.smartwallet.exception.InsufficientBalanceException;
import com.smartwallet.smartwallet.exception.ResourceNotFoundException;
import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.model.Wallet;
import com.smartwallet.smartwallet.repository.UserRepository;
import com.smartwallet.smartwallet.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    public WalletBalanceDto getBalance(String email) {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found!"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        WalletBalanceDto dto=new WalletBalanceDto();
        dto.setBalance(wallet.getBalance());
        return dto;
    }



    public WalletBalanceDto addMoney(String email, AddMoneyDto dto) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found!"));
        Wallet wallet=walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found!"));

        wallet.setBalance(wallet.getBalance().add(dto.getAmount()));//add the amount
        walletRepository.save(wallet);//save in the db first

        //return the dto object
        WalletBalanceDto walletBalanceDto=new WalletBalanceDto();
        walletBalanceDto.setBalance(wallet.getBalance());
        return walletBalanceDto;

    }


    @Transactional
    public String transferMoney(String email, TransferMoneyDto dto) {

        User sender = userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("Sender not found!"));

        Wallet senderWallet= walletRepository.findByUser(sender)
                .orElseThrow(()->new ResourceNotFoundException(" Sender Wallet not found!"));

        User receiver =userRepository.findByEmail(dto.getReceiverEmail())
                .orElseThrow(()->new ResourceNotFoundException("Receiver not found!"));

        Wallet receiverWallet=walletRepository.findByUser(receiver)
                .orElseThrow(()->new ResourceNotFoundException("Receiver Wallet not found!"));

        BigDecimal amount=dto.getAmount();

        if(senderWallet.getBalance().compareTo(amount) < 0)
        {
            throw new InsufficientBalanceException("Insufficient Balance!");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        walletRepository.save(senderWallet);
        receiverWallet.setBalance(receiverWallet.getBalance().add(amount));
        walletRepository.save(receiverWallet);

        return " Transaction Successful!";

    }
}
