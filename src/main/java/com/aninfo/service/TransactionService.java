package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;

import com.aninfo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction createTransaction(Double amount, String type, Long cbu) {
        if(type.equals("deposit")){
            accountService.deposit(cbu, amount);
        }else if(type.equals("withdraw")){
            accountService.withdraw(cbu, amount);
        }else{
            throw new InvalidTransactionTypeException("Transaction not valid. Try 'deposit' or 'withdraw'.");
        }

        Transaction transaction = new Transaction(amount, type, cbu);
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu) {
        List<Transaction> transactionsByCbu = new ArrayList<Transaction>();
        List<Transaction> allTransaction = transactionRepository.findAll();

        for (Transaction transaction: allTransaction) {
            if (transaction.getCbu() == cbu) {
                transactionsByCbu.add(transaction);
            }
        }
        return transactionsByCbu;
    }
}
