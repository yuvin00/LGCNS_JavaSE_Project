package mylab.bank.entity;

public class SavingsAccount extends Account {
    private final double interestRate;

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        if (!Double.isFinite(interestRate) || interestRate < 0) {
            throw new IllegalArgumentException("이자율은 0 이상이어야 합니다.");
        }
        this.interestRate = interestRate;
    }

    // 이자율은 3.0처럼 퍼센트 단위로 받습니다.
    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        if (interest > 0) {
            deposit(interest);
        }
        System.out.println("이자 " + interest + "원이 적용되었습니다. 현재 잔액: " + getBalance() + "원");
    }

    @Override
    public String toString() {
        return super.toString() + ", 이자율: " + interestRate + "%";
    }
}
