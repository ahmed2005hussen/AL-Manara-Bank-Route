public class FixedDepositAccount extends Account{







    @Override
    public boolean withdraw(double amount) {
        return false;
    }


    //A fixed deposit is money placed in the bank for a specified number of months.
    //• The account has an interest rate.
    //• The account has a duration in months.
    //• The system tracks how many months have passed.
    //• Money cannot be withdrawn before the account reaches maturity.
    //• If a withdrawal is attempted too early, the program must reject it and show how many months remain.
    //• After the maturity period is reached, withdrawal is allowed according to the account's rules.



}
