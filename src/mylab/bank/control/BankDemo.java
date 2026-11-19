package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.CheckingAccount;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        SavingsAccount first = bank.createSavingsAccount("홍길동", 10000, 3.0);
        CheckingAccount second = bank.createCheckingAccount("김철수", 20000, 5000);
        SavingsAccount third = bank.createSavingsAccount("이영희", 30000, 2.0);
        bank.displayAllAccounts();

        try {
            System.out.println("\n=== 입금/출금 테스트 ===");
            bank.deposit(first.getAccountNumber(), 5000);
            bank.withdraw(second.getAccountNumber(), 3000);

            System.out.println("\n=== 이자 적용 테스트 ===");
            first.applyInterest();

            System.out.println("\n=== 계좌 이체 테스트 ===");
            bank.transfer(third.getAccountNumber(), second.getAccountNumber(), 5000);
            bank.displayAllAccounts();
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 예외 처리 테스트 ===");
        testWithdrawal(bank, second.getAccountNumber(), 6000);
        testWithdrawal(bank, first.getAccountNumber(), 20000);
        testWithdrawal(bank, "AC9999", 1000);
    }

    private static void testWithdrawal(Bank bank, String accountNumber, double amount) {
        try {
            bank.withdraw(accountNumber, amount);
        } catch (WithdrawalLimitExceededException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
