public class Customer {

    private int customerId;
    private String fullName;
    private String nationalId;
    private String phoneNumber;
    private CustomerTiers customerTiers;
    private Account[] accounts;

    private static int nextCustomerNumber = 0;
    private int accountCount;
    private static final int MAX_ACCOUNTS = 5;


    public Customer(String fullName, String nationalId, String phoneNumber, CustomerTiers customerTiers) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.customerTiers = customerTiers;
        customerId = generateId();
        accounts = new Account[MAX_ACCOUNTS];
    }

    public boolean canAddMoreAccounts(){
        return accountCount < MAX_ACCOUNTS;
    }

    private static int generateId() {
        return nextCustomerNumber++;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerTiers getCustomerTiers() {
        return customerTiers;
    }

    public void setCustomerTiers(CustomerTiers customerTiers) {
        this.customerTiers = customerTiers;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void addAccount(Account a) {
        if (accountCount == MAX_ACCOUNTS) return;
        accounts[accountCount++] = a;
    }

    @Override
    public String toString() {
        return "==============================\n" +
                "Customer ID      : " + customerId + "\n" +
                "Full Name        : " + fullName + "\n" +
                "National ID      : " + nationalId + "\n" +
                "Phone Number     : " + phoneNumber + "\n" +
                "Customer Tier    : " + customerTiers + "\n" +
                "Accounts Count   : " + accountCount + "\n" +
                "==============================";
    }


    //The system generates a unique customer ID.
    // The customer ID must never be typed manually by the employee.
}
