public class FixedDepositAccount extends Account {

    private double interestRate;
    private int durationMonths;
    private int monthsPassed;
    private static final double MIN_TRANSACTION = 10;

    public FixedDepositAccount(Customer owner, double balance, AccountStatus accountStatus, double interestRate, int durationMonths) {
        super(owner, balance, accountStatus, MIN_TRANSACTION);
        this.interestRate = interestRate;
        setDurationMonths(durationMonths);
        this.monthsPassed = 0;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0) {
            this.interestRate = interestRate;

        }
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        if (durationMonths > 0) {
            this.durationMonths = durationMonths;
        }
    }

    public int getMonthsPassed() {
        return monthsPassed;
    }

    public void passOneMonth() {
        if (!isMature()) monthsPassed++;
    }

    public boolean isMature() {
        return monthsPassed >= durationMonths;
    }

    public int getRemainingMonths() {
        if (isMature()) {
            return 0;
        }
        return durationMonths - monthsPassed;
    }

    @Override
    public boolean withdraw(double amount) {
        if (getAccountStatus() != AccountStatus.ACTIVE || amount <= 0 || !isMature() || amount > getBalance()) {
            return false;
        }

        setBalance(getBalance() - amount);
        incrementTransactionCount();
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nInterest Rate     : " + interestRate + "%" + "\nDuration Months   : " + durationMonths + "\nMonths Passed     : " + monthsPassed + "\nMonths Remaining  : " + getRemainingMonths() + "\nMature           : " + (isMature() ? "Yes" : "No");
    }

}
