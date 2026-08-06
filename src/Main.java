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
        System.out.println("0 Exit");

        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice >= 0 && choice <= 10 ? choice : -1;
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
        if (!Bank.isUniqueNationalId(nationalId)) {
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

            }
        }


    }
}