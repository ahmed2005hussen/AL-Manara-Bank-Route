public class CurrentAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 1000;
    private static final double MIN_TRANSACTION = 10;
    public CurrentAccount(Customer owner, double balance, AccountStatus accountStatus) {
        super(owner, balance, accountStatus , MIN_TRANSACTION);
    }

    @Override
    public boolean withdraw(double amount) {

        if (getAccountStatus() != AccountStatus.ACTIVE || amount <= 0) {
            return false;
        }
        double newBalance = getBalance() - amount;
        if (newBalance >= -OVERDRAFT_LIMIT) {
            setBalance(newBalance);
            incrementTransactionCount();
            return true;
        }
        return false;
    }

    public boolean isUsingOverdraft() {
        return getBalance() < 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nOverdraft Limit : " + OVERDRAFT_LIMIT +
                "\nUsing Overdraft : " + (isUsingOverdraft() ? "Yes" : "No");
    }

    //A current account is allowed to use an overdraft up to a predefined limit.
    //• The balance may become negative.
    //• The balance must never go below the negative overdraft limit.
    //• Example: if the overdraft limit is $1,000, a balance of $200 may be reduced to -$800, but a withdrawal that would
    //produce -$801 must be rejected.
    //• The system should indicate whether the account is currently using its overdraft.


}
