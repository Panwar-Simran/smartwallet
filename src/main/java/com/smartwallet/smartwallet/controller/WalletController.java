package com.smartwallet.smartwallet.controller;

import com.smartwallet.smartwallet.dto.AddMoneyDto;
import com.smartwallet.smartwallet.dto.TransferMoneyDto;
import com.smartwallet.smartwallet.dto.WalletBalanceDto;
import com.smartwallet.smartwallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;


    //check balance
    @GetMapping("/balance")
    ResponseEntity<WalletBalanceDto>getBalance(Principal principal)
    {
        WalletBalanceDto walletBalanceDto =walletService.getBalance(principal.getName());

        return new ResponseEntity<>(walletBalanceDto, HttpStatus.OK);
    }

    //add balance
    @PostMapping("/add-money")

    ResponseEntity<WalletBalanceDto>addMoney(
            Principal principal,
            @Valid @RequestBody AddMoneyDto dto)
    {
        WalletBalanceDto walletBalanceDto=walletService.addMoney(principal.getName(),dto);
        return new ResponseEntity<>(walletBalanceDto,HttpStatus.OK);
    }

    //transfer money

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            Principal principal,
            @Valid @RequestBody TransferMoneyDto dto
    ) {
       String result= walletService.transferMoney(principal.getName(), dto);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
