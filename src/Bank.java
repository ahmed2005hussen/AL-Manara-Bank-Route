public class Bank {
    private  Customer[] customers;
    private  Account[] accounts;

    public static final double SAVING_MIN = 500;
    public static final double CURRENT_MIN = 1000;
    public static final double FIXED_MIN = 5000;

    private  int numberOfCustomers = 0;
    private  int numberOfAccounts = 0;

    private final static int CUSTOMERS_CAPACITY = 20;

    // 20 customers each one can open 5 account so total is 20 * 5
    private final static int ACCOUNTS_CAPACITY = 100;

    public Bank() {
        customers = new Customer[CUSTOMERS_CAPACITY];
        accounts = new Account[ACCOUNTS_CAPACITY];
    }

    public  boolean isTherePlaceCustomer() {
        return numberOfCustomers < CUSTOMERS_CAPACITY;
    }

    public  boolean isTherePlaceAccount() {
        return numberOfAccounts < ACCOUNTS_CAPACITY;
    }

    public  boolean isUniqueNationalId(String id) {

        for (Customer c : customers) {
            if (c != null && c.getNationalId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public Customer regiterCustomer(String fullName, String nationalId,
                                    String phoneNumber, CustomerTiers customerTiers) {


        Customer customer = new Customer(fullName, nationalId, phoneNumber, customerTiers);

        customers[numberOfCustomers++] = customer;
        return customer;
    }

    public Customer findCustomerById(int customerId) {
        for (Customer c : customers) {
            if (c != null && c.getCustomerId() == customerId) {
                return c;
            }
        }
        return null;
    }

    public Account findAccountById(int accountId) {
        for (Account a : accounts) {
            if (a != null && a.getAccountNumber() == accountId) {
                return a;
            }
        }
        return null;
    }

    public Account openAccount(Customer c, Account a) {
        c.addAccount(a);
        a.setOwner(c);
        accounts[numberOfAccounts++] = a;
        return a;
    }

    public void printCustomerAccounts(int id) {
        Customer c = findCustomerById(id);

        if (c == null) {
            System.out.println("Customer does not exist");
            return;
        }

        System.out.println(c);

        int count = 0;
        double totalBalance = 0;
        for (Account a : c.getAccounts()) {
            if (a != null) {
                count++;
                System.out.println(count + ". " + a);
                totalBalance += a.getBalance();
            }
        }
        if (count == 0) {
            System.out.println("have no accounts");
            return;
        }
        System.out.println("His total balance: " + totalBalance);

    }

    public void displayAccountsByType(Class<?> type) {

        int count = 0;
        double totalBalance = 0;

        for (Account account : accounts) {

            if (account != null && type.isInstance(account)) {

                System.out.println(account);
                System.out.println("----------------------------");

                count++;
                totalBalance += account.getBalance();
            }
        }

        System.out.println("Number of Accounts : " + count);
        System.out.println("Total Balance      : " + totalBalance);
    }

    public void displayAllBranchAccounts() {
        System.out.println("Accounts: ");

        if (numberOfAccounts == 0) {
            System.out.println("this branch have no accounts");
            return;
        }
        for (Account a : accounts) {
            if (a != null) {
                System.out.println(a);
            }
        }

    }

}
