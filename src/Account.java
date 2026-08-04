public abstract class Account {

    private int accountNumber ;
    private Customer owner ;
    private double balance;
    private AccountStatus accountStatus;

    protected static int count = 0 ;

    public Account(){}

    public Account(Customer owner , double balance ,AccountStatus accountStatus){
        this.balance = balance;
        this.owner = owner;
        this.accountStatus = accountStatus;
        accountNumber = generateId();
    }

    public int generateId(){
        return count++ ;
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void setBalance(double balance) ;

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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", owner=" + owner +
                ", balance=" + balance +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
