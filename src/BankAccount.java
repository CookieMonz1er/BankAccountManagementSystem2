public class BankAccount {
    private String ownerName;
    private long iban;
    private double balance;

    private int count;
    private String[] transactionHistory;


    public BankAccount(String ownerName, long iban) {
        this.ownerName = ownerName;
        this.iban = iban;
        balance = 0;

        this.count = 0;
        this.transactionHistory = new String[10];
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public long getIban() {
        return iban;
    }

    public void setIban(long iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String[] getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(String[] transactionHistory) {
        this.transactionHistory = transactionHistory;
    }


    private void addTransaction(String record) {
        if (record == null) return;

        if (count < transactionHistory.length) {
            transactionHistory[count] = record;
            count++;
        } else {
            for (int i = 0; i < transactionHistory.length - 1; i++) {
                transactionHistory[i] = transactionHistory[i + 1];
            }
            transactionHistory[transactionHistory.length - 1] = record;
        }


    }

    public void deposit(double amount) {
        if (amount <= 0) {
            addTransaction("FAILED_DEPOSIT: " + amount);
            return;
        }
        balance += amount;
        addTransaction("DEPOSIT: " + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance || amount <= 0) {
            addTransaction("FAILED_WITHDRAW: " + amount);
            return;
        }
        balance -= amount;
        addTransaction("WITHDRAW: " + amount);
    }

    public void transferTo(BankAccount receiver, double amount) {

        if (receiver == null) {
            addTransaction("FAILED_TRANSFER: " + amount);
            return;
        }

        double total = (amount + Bank.calculateTransferFee(amount));

        if (amount > 0 && total <= this.getBalance()  ) {
            balance -= total;
            receiver.setBalance(receiver.getBalance() + amount);
            receiver.addTransaction("GOT: " + amount);
            addTransaction("SENT: " + total);
        } else {
            addTransaction("FAILED_TRANSFER: " + amount);
        }
    }

    public void displayAccountInfo() {
        System.out.println("(" + ownerName + ", " + iban + ", " + balance + ")");
    }

    public void displayHistory() {
        if (count == 0) {
            System.out.println("No transactions");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(ownerName + " " + transactionHistory[i]);
        }
    }
}