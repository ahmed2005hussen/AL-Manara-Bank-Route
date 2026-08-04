import java.util.Arrays;
import java.util.List;

public class Customer {

    private int customerId;
    private String fullName;
    private String nationalId;
    private String phoneNubmer;
    private CustomerTiers customerTiers;
    private Account[] accounts;
    private static int count = 0;
    private static int i = 0;

    public Customer(String fullName, String nationalId, String phoneNubmer, CustomerTiers customerTiers) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.phoneNubmer = phoneNubmer;
        this.customerTiers = customerTiers;
        customerId = generateId();
        accounts = new Account[10];

    }


    public Customer(String fullName, String nationalId, CustomerTiers customerTiers) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.customerTiers = customerTiers;
        phoneNubmer = "Not obtain";
        customerId = generateId();
        accounts = new Account[10];
    }


    public int generateId() {
        return count++;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNubmer() {
        return phoneNubmer;
    }

    public void setPhoneNubmer(String phoneNubmer) {
        this.phoneNubmer = phoneNubmer;
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
        if (i == 10) return;
        accounts[i++] = a;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNubmer='" + phoneNubmer + '\'' +
                ", customerTiers=" + customerTiers +
                ", accounts=" + Arrays.toString(accounts) +
                '}';
    }
    //The system generates a unique customer ID.
    // The customer ID must never be typed manually by the employee.
}
