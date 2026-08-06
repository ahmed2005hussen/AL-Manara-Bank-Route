public abstract class Account {

    private int accountNumber;
    private Customer owner;
    private double balance;
    private AccountStatus accountStatus;
    private int transactionCount;
    protected static int nextAccountNumber = 0;
    private double minDeposite;

    private static int generateId() {
        return nextAccountNumber++;
    }


    public Account() {
    }

    public Account(Customer owner, double balance, AccountStatus accountStatus , double minDeposite) {
        this.balance = balance;
        this.owner = owner;
        this.accountStatus = accountStatus;
        accountNumber = generateId();
        this.transactionCount = 0;
        this.minDeposite = minDeposite;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    protected void incrementTransactionCount() {
        transactionCount++;
    }

    public double getMinDeposite() {
        return minDeposite;
    }


    public boolean deposit(double amount) {
        if (amount > 0 && accountStatus == AccountStatus.ACTIVE && minDeposite <= amount) {
            setBalance(getBalance() + amount);
            incrementTransactionCount();
            return true;
        }
        return false;
    }

    public abstract boolean withdraw(double amount);

    @Override
    public String toString() {
        return "==============================\n" +
                "Account Type      : " + getClass().getSimpleName() + "\n" +
                "Account Number    : " + accountNumber + "\n" +
                "Owner             : " + owner.getFullName() + "\n" +
                "Balance           : " + balance + "\n" +
                "Account Status    : " + accountStatus + "\n" +
                "Transactions      : " + transactionCount + "\n" +
                "==============================";
    }
}
