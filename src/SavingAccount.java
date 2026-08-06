public class SavingAccount extends Account {

    private double annualInterestRate;
    private int monthlyWithdrawCount;


    public SavingAccount(Customer owner, double balance, AccountStatus accountStatus,
                         double annualInterestRate) {
        super(owner, balance, accountStatus);
        this.annualInterestRate = annualInterestRate;
        this.monthlyWithdrawCount = 0;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        if (annualInterestRate >= 0) {
            this.annualInterestRate = annualInterestRate;
        }
    }

    public int getMonthlyWithdrawCount() {
        return monthlyWithdrawCount;
    }

    public void newMonth() {
        monthlyWithdrawCount = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (getAccountStatus() != AccountStatus.ACTIVE || amount <= 0 ||
                amount > getBalance()) {
            return false;
        }

        setBalance(getBalance() - amount);
        monthlyWithdrawCount++;
        incrementTransactionCount();
        return true;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nAnnual Interest Rate : " + annualInterestRate + "%" +
                "\nMonthly Withdrawals  : " + monthlyWithdrawCount;
    }

}
