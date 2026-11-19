package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
    private final String accountNumber;
    private final String ownerName;
    private double balance;

    protected Account(String accountNumber, String ownerName, double balance) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("초기 잔액은 0 이상이어야 합니다.");
        }
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    protected static void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("거래 금액은 0보다 커야 합니다.");
        }
    }

    public void deposit(double amount) {
        validateAmount(amount);
        if (!Double.isFinite(balance + amount)) {
            throw new IllegalArgumentException("입금 후 잔액이 허용 범위를 초과합니다.");
        }
        balance += amount;
        System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + balance + "원");
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        validateAmount(amount);
        if (amount > balance) {
            throw new InsufficientBalanceException("잔액이 부족합니다. 현재 잔액: " + balance + "원");
        }
        balance -= amount;
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + balance + "원");
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber + ", 소유자: " + ownerName + ", 잔액: " + balance + "원";
    }
}
