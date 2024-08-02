package com.project1.transaction;

import com.project1.offer.OfferRepository;
import com.project1.offer.data.Offer;
import com.project1.transaction.data.Transaction;
import com.project1.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project1.transaction.data.TransactionDTO;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final TransactionMapper transactionMapper;
    private final OfferRepository offerRepository;

    public List<Transaction> getTransactionsBySenderId(Long senderId) {
        return transactionRepository.findBySenderUserId(senderId);
    }

    public List<Transaction> getTransactionsByReceiverId(Long receiverId) {
        return transactionRepository.findByReceiverUserId(receiverId);
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return transactionRepository.findByType(type);
    }
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow();
    }


    public Transaction createAdminTransaction(TransactionDTO transactiondto) {
        Transaction transaction = transactionMapper.toEntity(transactiondto);
        Random random = new Random();
        long transactionNumber = random.nextInt(900000000) + 100000000L;
        transaction.setTransactionNumber(transactionNumber);

        switch (transaction.getType()) {
            case DEPOSIT:
                return depositTransaction(transaction);
            case WITHDRAW:
                return withdrawTransaction(transaction);
            default:
                throw new UnsupportedOperationException("Unsupported transaction type: " + transaction.getType());
        }
    }

    public Transaction createTransaction(TransactionDTO transactiondto) {
        Transaction transaction = transactionMapper.toEntity(transactiondto);
        Random random = new Random();
        long transactionNumber = random.nextInt(900000000) + 100000000L;
        transaction.setTransactionNumber(transactionNumber);
        switch (transaction.getType()) {
            case TRANSFER:
                return transferTransaction(transaction);
            case HOLD:
                return holdTransaction(transaction);
            case UNHOLD:
                return unholdTransaction(transaction);
            default:
                throw new UnsupportedOperationException("Unsupported transaction type: " + transaction.getType());
        }
    }

//    public String acceptOffer(AcceptOfferRequest acceptOfferRequest)
//    {
//        Offer offer = offerRepository.findById(acceptOfferRequest.offerId);
//
//
//
//    }


    private Transaction depositTransaction(Transaction transaction) {
        walletService.addTotalBalance(transaction.getReceiverUserId(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }

    private Transaction withdrawTransaction(Transaction transaction) {
        walletService.subtractTotalBalance(transaction.getReceiverUserId(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }

    private Transaction transferTransaction(Transaction transaction) {
        walletService.subtractTotalHeldBalance(transaction.getSenderUserId(), transaction.getAmount());
        walletService.addTotalHeldBalance(transaction.getReceiverUserId(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }

    private Transaction holdTransaction(Transaction transaction) {
        walletService.subtractTotalBalance(transaction.getReceiverUserId(), transaction.getAmount());
        walletService.addTotalHeldBalance(transaction.getReceiverUserId(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }

    private Transaction unholdTransaction(Transaction transaction) {
        walletService.subtractTotalHeldBalance(transaction.getReceiverUserId(), transaction.getAmount());
        walletService.addTotalBalance(transaction.getReceiverUserId(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }





}