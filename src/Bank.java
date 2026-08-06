public class Bank {
    private static Customer[] customers;
    private static Account[] accounts;

    public static final double SAVING_MIN = 500;
    public static final double CURRENT_MIN = 1000;
    public static final double FIXED_MIN = 5000;

    private static int numberOfCustomers = 0;
    private static int numberOfAccounts = 0;

    private final static int CUSTOMERS_CAPACITY = 20;

    // 20 customers each one can open 5 account so total is 20 * 5
    private final static int ACCOUNTS_CAPACITY = 100;

    public Bank() {
        customers = new Customer[CUSTOMERS_CAPACITY];
        accounts = new Account[ACCOUNTS_CAPACITY];
    }

    public static boolean isTherePlaceCustomer() {
        return numberOfCustomers < CUSTOMERS_CAPACITY;
    }

    public static boolean isTherePlaceAccount() {
        return numberOfAccounts < ACCOUNTS_CAPACITY;
    }

    public static boolean isUniqueNationalId(String id) {

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

    public Customer findById(int customerId) {
        for (Customer c : customers) {
            if (c != null && c.getCustomerId() == customerId) {
                return c;
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

}
