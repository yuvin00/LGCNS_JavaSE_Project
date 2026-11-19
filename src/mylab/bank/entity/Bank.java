package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private final List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1000;
    }

    public SavingsAccount createSavingsAccount(String ownerName, double balance, double interestRate) {
        SavingsAccount account = new SavingsAccount("AC" + nextAccountNumber, ownerName, balance, interestRate);
        accounts.add(account);
        nextAccountNumber++;
        System.out.println("저축 계좌가 생성되었습니다: " + account);
        return account;
    }

    public CheckingAccount createCheckingAccount(String ownerName, double balance, double withdrawalLimit) {
        CheckingAccount account = new CheckingAccount("AC" + nextAccountNumber, ownerName, balance, withdrawalLimit);
        accounts.add(account);
        nextAccountNumber++;
        System.out.println("체킹 계좌가 생성되었습니다: " + account);
        return account;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        // 받는 계좌를 확인한 뒤 보내는 계좌에서 출금합니다.
        if (fromAccount == toAccount) {
            throw new IllegalArgumentException("같은 계좌로는 이체할 수 없습니다.");
        }
        if (!Double.isFinite(toAccount.getBalance() + amount)) {
            throw new IllegalArgumentException("이체 후 받는 계좌의 잔액이 허용 범위를 초과합니다.");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println(amount + "원이 " + fromAccountNumber + "에서 " + toAccountNumber + "로 송금되었습니다.");
    }

    public void displayAllAccounts() {
        System.out.println("\n=== 모든 계좌 목록 ===");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("===================");
    }
}
