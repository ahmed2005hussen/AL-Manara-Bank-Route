import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    Bank manaraBank = new Bank();

    int menu() {

        System.out.println("1 Register New Customer");
        System.out.println("2 Open New Account");
        System.out.println("3 Deposit Money");
        System.out.println("4 Withdraw Money");
        System.out.println("5 Transfer Between Accounts");
        System.out.println("6 Display Customer Accounts");
        System.out.println("7 Display All Branch Accounts");
        System.out.println("8 Search Account by Number");
        System.out.println("9 Search Accounts by Type");
        System.out.println("10 Close an Account");
        System.out.println("11 pass month for testing mature");
        System.out.println("0 Exit");

        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice >= 0 && choice <= 11 ? choice : -1;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {

        if (phoneNumber.length() < 7 || phoneNumber.length() > 15) {
            return false;
        }

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void registerCustomer() {

        if (!manaraBank.isTherePlaceCustomer()) {
            System.out.println("Sorry now our capacity if full");
            System.out.println("-------------------------------");
            return;
        }

        System.out.print("Enter the Customer full name: ");

        String fullName = sc.nextLine();
        if (fullName.isBlank()) {
            System.out.println("Invalid Name, try again with real name");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.print("Enter the Customer National id: ");

        String nationalId = sc.nextLine();
        if (nationalId.isBlank()) {
            System.out.println("Invalid national Id, try again with real national Id");
            System.out.println("--------------------------------------");
            return;
        }
        if (!manaraBank.isUniqueNationalId(nationalId)) {
            System.out.println("This Customer registered before");
            System.out.println("--------------------------------------");
            return;
        }

        int choice;
        do {
            System.out.println("Customer Tier: ");
            System.out.println("1.STANDARD");
            System.out.println("2.SILVER");
            System.out.println("3.GOLD");
            System.out.print("Enter The choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice <= 0 || choice > 3) {
                System.out.println("Invalid choice, try again with number between 1 - 3");
                continue;
            }
            break;
        } while (true);

        CustomerTiers customerTier = CustomerTiers.STANDARD;

        switch (choice) {
            case 1 -> customerTier = CustomerTiers.STANDARD;
            case 2 -> customerTier = CustomerTiers.SILVER;
            case 3 -> customerTier = CustomerTiers.GOLD;
        }


        choice = 0;
        do {
            System.out.println("Do you want to add phone number?");
            System.out.println("1.yes");
            System.out.println("2.No");
            System.out.print("Enter The choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice <= 0 || choice > 2) {
                System.out.println("Invalid choice, try again with number between 1 - 2");
                continue;
            }
            break;
        } while (true);

        String phoneNumber = "-";

        if (choice == 1) {
            do {
                System.out.print("Enter your phone number: ");
                phoneNumber = sc.nextLine();

                if (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid phone number, number have 7 to 15 digits only");
                    continue;
                }

                break;

            } while (true);
        }

        Customer customer = manaraBank.regiterCustomer(fullName, nationalId, phoneNumber, customerTier);

        System.out.println("Created Successfully: ");
        System.out.println(customer);
    }


    public double getBalance(double minValue) {
        double balance;
        do {
            System.out.print("Enter Your balance: ");
            balance = sc.nextDouble();
            sc.nextLine();

            if (balance > 0 && minValue <= balance) {
                return balance;
            }

            System.out.println("Balance Should be greater than: " + minValue);
            System.out.println("Do you want to enter another value? ");
            System.out.println("1. yes ");
            System.out.println("2. No");
            System.out.print("Enter your choice 1 or 2: ");
            int flag = sc.nextInt();
            sc.nextLine();
            if (flag == 1) {
                continue;
            }
            if (flag == 2) {
                return -1;
            }
            System.out.println("Invalid input enter 1 or 2 ");

        } while (true);

    }

    public void openAccount() {

        if (!manaraBank.isTherePlaceAccount()) {
            System.out.println("Sorry now our capacity if full");
            System.out.println("-------------------------------");
            return;
        }

        System.out.print("Enter Customer id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Customer c = manaraBank.findCustomerById(id);
        if (c == null) {
            System.out.println("Customer ID does not exist ");
            System.out.println("---------------------------");
            return;
        }

        if (!c.canAddMoreAccounts()) {
            System.out.println("This customer can not open more account, reach to the limit");
            System.out.println("-----------------------------------------------------------");
            return;
        }

        int choice = 0;
        do {
            System.out.println("Account Type");
            System.out.println("1. Saving Account");
            System.out.println("2. Current Account");
            System.out.println("3. Fixed Deposit Account");
            System.out.print("Enter Your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice <= 0 || choice > 3) {
                System.out.println("Invalid Input try number between 1 - 3");
                continue;
            }
            break;
        } while (true);


        if (choice == 1) {
            double balance = getBalance(Bank.SAVING_MIN);
            if (balance == -1) {
                System.out.println("Thank You :)");
                System.out.println("------------");
                return;
            }

            double annual = 0;
            do {
                System.out.print("Enter Annual Interest Rate: ");
                annual = sc.nextDouble();
                sc.nextLine();
            } while (annual < 0);

            Account account = new SavingAccount(c, balance, AccountStatus.ACTIVE, annual);

            System.out.println("Add successfully: ");
            System.out.println(manaraBank.openAccount(c, account));

        } else if (choice == 2) {

            double balance = getBalance(Bank.CURRENT_MIN);
            if (balance == -1) {
                System.out.println("Thank You :)");
                System.out.println("------------");
                return;
            }
            Account account = new CurrentAccount(c, balance, AccountStatus.ACTIVE);

            System.out.println("Add successfully: ");
            System.out.println(manaraBank.openAccount(c, account));

        } else {

            double balance = getBalance(Bank.FIXED_MIN);
            if (balance == -1) {
                System.out.println("Thank You :)");
                System.out.println("------------");
                return;
            }

            double interestRate = 0;
            do {
                System.out.print("Enter Annual Interest Rate: ");
                interestRate = sc.nextDouble();
                sc.nextLine();
            } while (interestRate < 0);

            int durationMonths;
            do {
                System.out.print("Enter Duration Months: ");
                durationMonths = sc.nextInt();
                sc.nextLine();

                if (durationMonths <= 0) {
                    System.out.println("Duration must be at least one month.");
                }
            } while (durationMonths <= 0);

            Account account = new FixedDepositAccount(c, balance, AccountStatus.ACTIVE, interestRate, durationMonths);

            System.out.println("Add successfully: ");
            System.out.println(manaraBank.openAccount(c, account));
        }

        System.out.println("-------------------------------");

    }

    public void deposit() {

        System.out.print("Enter Account Id: ");
        int accountID = sc.nextInt();
        sc.nextLine();

        Account a = manaraBank.findAccountById(accountID);
        if (a == null) {
            System.out.println("Account Does not exit");
            System.out.println("-----------------------");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        if (amount <= 0) {
            System.out.println("Invalid amount should be grater than 0");
            System.out.println("---------------------------------------");
            return;
        }

        if (a.getAccountStatus() != AccountStatus.ACTIVE) {
            System.out.println("This acount is not active to deposit");
            System.out.println("---------------------------------------");
            return;
        }

        double current = a.getBalance();

        boolean isDone = a.deposit(amount);

        if (isDone) {
            System.out.println("Deposit Done successfully");
            System.out.println("Current balance = $" + current +
                    " Deposit = $" + amount + " New balance = $" + a.getBalance());
            System.out.println("------------------------");
            return;
        }
        System.out.println("Transaction failed");
        System.out.println("You should deposit more than or equal: " + a.getMinDeposite());
        System.out.println("-------------------------------------------");
    }

    public void withdraw() {

        System.out.print("Enter Account Number: ");
        int accountId = sc.nextInt();
        sc.nextLine();

        Account account = manaraBank.findAccountById(accountId);

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter Withdraw Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (account.withdraw(amount)) {
            System.out.println("Withdraw successful");
            System.out.println("New Balance: " + account.getBalance());

            if (account instanceof CurrentAccount current) {
                System.out.println("Using Overdraft: "
                        + (current.isUsingOverdraft() ? "Yes" : "No"));
            }
        } else {

            System.out.println("Withdrawal failed");

            if (account instanceof FixedDepositAccount fixed && !fixed.isMature()) {
                System.out.println("Months Remaining: "
                        + fixed.getRemainingMonths());
            }
        }

        System.out.println("-----------------------------");
    }

    public void transferMoney() {

        System.out.print("Enter from Account id: ");
        int from = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter to Account id: ");
        int to = sc.nextInt();
        sc.nextLine();

        if (from == to) {
            System.out.println("Accounts must be different ");
            return;
        }

        Account fromAccount = manaraBank.findAccountById(from);
        Account toAccount = manaraBank.findAccountById(to);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Account not found.");
            return;
        }


        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Amount must be a positive ");
            return;
        }

        if (toAccount.getAccountStatus() != AccountStatus.ACTIVE
                || amount < toAccount.getMinDeposite()) {
            System.out.println("Transfer failed");
            return;
        }


        if (!fromAccount.withdraw(amount)) {
            System.out.println("Transfer failed.");
            return;
        }


        if (!toAccount.deposit(amount)) {

            // Rollback
            fromAccount.deposit(amount);

            System.out.println("Transfer failed.");
            return;
        }

        System.out.println("Transfer completed successfully.");
    }

    public void printCustomerAccounts() {
        System.out.print("Enter customer id: ");
        int id = sc.nextInt();
        sc.nextLine();

        manaraBank.printCustomerAccounts(id);
    }

    public void displayAllBranchAccounts() {
        manaraBank.displayAllBranchAccounts();
    }

    public void searchByAccountId() {

        System.out.print("Enter Account ID: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        Account account = manaraBank.findAccountById(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            System.out.println("----------------------------");
            return;
        }

        System.out.println(account);
        System.out.println("----------------------------");
    }

    public void searchAccountByType() {

        int choice;

        do {

            System.out.println("Choose Account Type");
            System.out.println("1. Saving Account");
            System.out.println("2. Current Account");
            System.out.println("3. Fixed Deposit Account");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            if (choice >= 1 && choice <= 3)
                break;

            System.out.println("Invalid choice.");

        } while (true);

        switch (choice) {

            case 1 -> manaraBank.displayAccountsByType(SavingAccount.class);

            case 2 -> manaraBank.displayAccountsByType(CurrentAccount.class);

            case 3 -> manaraBank.displayAccountsByType(FixedDepositAccount.class);

        }

        System.out.println("----------------------------");
    }

    public void closeAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        Account account = manaraBank.findAccountById(accountNumber);

        if (account == null) {
            System.out.println("Account not found ");
            return;
        }

        if (account.getAccountStatus() == AccountStatus.CLOSED) {
            System.out.println("This account is already closed");
            return;
        }

        if (account.getBalance() != 0) {
            System.out.println("Account balance must be zero before closing.");
            return;
        }

        if (account instanceof FixedDepositAccount fixed && !fixed.isMature()) {
            System.out.println("This fixed deposit has not matured yet.");
            System.out.println("Months Remaining: " + fixed.getRemainingMonths());
            return;
        }

        account.setAccountStatus(AccountStatus.CLOSED);

        System.out.println("Account closed successfully.");
    }

    public void passMonth(){
        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        Account account = manaraBank.findAccountById(accountNumber);

        if (!(account instanceof FixedDepositAccount fixed)) {
            System.out.println("Fixed deposit account not found.");
            return;
        }

        fixed.passOneMonth();
        System.out.println("Months remaining: " + fixed.getRemainingMonths());
    }

    void main(String[] args) {
        System.out.println("Welcome in our bank");
        System.out.println("-------------------");

        loop:
        while (true) {
            int choice = menu();

            switch (choice) {
                case -1 -> System.out.println("Wrong input please enter number between 0-10");

                case 0 -> {
                    break loop;
                }

                case 1 -> registerCustomer();

                case 2 -> openAccount();

                case 3 -> deposit();

                case 4 -> withdraw();

                case 5 -> transferMoney();

                case 6 -> printCustomerAccounts();

                case 7 -> displayAllBranchAccounts();

                case 8 -> searchByAccountId();

                case 9 -> searchAccountByType();

                case 10 -> closeAccount();

                case 11 -> passMonth();
            }
        }


    }
}