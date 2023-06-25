import java.util.Scanner;

class Account {
    protected String U_id;
    protected String U_Pass;
    Scanner sc = new Scanner(System.in);

    void createAcc() {
        System.out.println("Enter the Username:");
        U_id = sc.nextLine();
        System.out.println("Enter the Password:");
        U_Pass = sc.nextLine();
        System.out.println(" Congratulations! Account Created Sucessfully");
    }
}

class LoginAcc extends Account {
    void login() {
        String id;
        do {
            System.out.println("Enter the Username:");
            id = sc.nextLine();
            if (U_id.equals(id)) {
                String pass;
                do {
                    System.out.println("Enter the Password:");
                    pass = sc.nextLine();
                    if (pass.equals(U_Pass)) {
                        System.out.println("Login successful !");
                    }
                    else {
                        System.out.println("Password is Wrong! Please Enter the correct password");
                    }
                }
                while (!pass.equals(U_Pass));
            }
            else {
                System.out.println("Password is Wrong ! Please Enter the correct password");
            }
        }
        while (!U_id.equals(id));
    }
}
class Deposit {
    int amt;
    int Balance = 200000;
    int prevtransaction = 0;
    String Transaction_history = "";
    Scanner sc = new Scanner(System.in);

    void Display() {
        System.out.println("Enter the amount to be deposited:");
        amt = sc.nextInt();
        if (amt != 0) {
            Balance = Balance + amt;
            prevtransaction = amt;
            String str = amt + "Rs deposited\n";
            Transaction_history = Transaction_history.concat(str);
            System.out.println("Amount is Successfully Deposited !");
        }
    }
}

class Withdraw extends Deposit {
    void Cash() {
        System.out.println("Enter the amount to be withdrawn:");
        amt = sc.nextInt();
        if (Balance > amt) {
            if (amt <= 20000) {
                prevtransaction++;
                Balance = Balance - amt;
                prevtransaction = prevtransaction - amt;
                System.out.println("Amount is withdrawn successfully !");
                String str = amt + "Rs withdrawed\n";
                Transaction_history = Transaction_history.concat(str);
            }
            else {
                System.out.println("Sorry ! But the limit is 20000");
            }
        }
        else {
            System.out.println("Insufficient amount to be withdrawn !");
        }
    }
}

class Transfer extends Withdraw {
    void forward() {
        int receiver_accbalance = 0;
        System.out.println("Enter the amount to be transferred: ");
        amt = sc.nextInt();
        if (Balance < amt) {
            System.out.println("You don't have sufficient balance to transfer the amount");
        }
        else {
            System.out.println("Enter the account no. of the receiver:");
            this.Balance = this.Balance - amt;
            receiver_accbalance += amt;
            System.out.println("Your account balance becomes $ " + this.Balance);
            System.out.println("Account Balance of receiver becomes $ " + receiver_accbalance);
            String str = amt + " Rs transfered";
            Transaction_history = Transaction_history.concat(str);
        }
    }
}
class CheckBalance extends Transfer {
    void check() {
        System.out.println("\nAvailable Balance is :" + Balance + " Rs");
    }
}
class TransactionHistory extends CheckBalance {
    void History() {
        if (prevtransaction == 0) {
            System.out.println("\nEmpty");
        }
        else {
            System.out.println("\n" + Transaction_history);
        }
    }
}

class AtmInterface {
    public static void main(String[] args) {
        System.out.println("WELCOME TO SBI BANK");
        LoginAcc l = new LoginAcc();
        l.createAcc();
        System.out.println("WELCOME BACK TO SBI BANK");
        System.out.println("------");
        System.out.println("WELCOME TO THE LOGIN SECTOR");
        l.login();

        int choice;
        Scanner sc = new Scanner(System.in);
        TransactionHistory t = new TransactionHistory();
        while (true) {
            System.out.println("\n");
            System.out.println("1. Check Bank Balance");
            System.out.println("2. Deposit the amount");
            System.out.println("3. Withdraw the amount");
            System.out.println("4. Previous Transaction");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.println("=================================================");
            System.out.println("Enter your Choice:");
            System.out.println("=================================================");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> t.check();
                case 2 -> t.Display();
                case 3 -> t.Cash();
                case 4 -> t.History();
                case 5 -> t.forward();
                case 6 -> {
                    System.out.println("Thank you for using our service");
                    System.exit(choice);
                }
                default -> System.out.println("Enter your valid choice!");
            }

        }

    }

}
