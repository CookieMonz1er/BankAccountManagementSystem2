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


    public void addTransaction(String record) {
        transactionHistory[count] = record;
        count++;

        if (count == transactionHistory.length) {
            for (int i = 0; i < count - 1; i++) {
                transactionHistory[i] = transactionHistory[i + 1];
            }
            transactionHistory[count - 1] = record;
        }

    }

    public void deposit(int amount) {
        balance += amount;
        addTransaction("DEPOSIT: " + amount);
    }

    public void withdraw(double amount) {
        if (amount > balance){
            addTransaction("FAILED: " + amount);
        }
        balance -= amount;
        addTransaction("WITHDRAW: " + amount);
    }

    public void transferTo(long iban, BankAccount receiver, int amount){
        if(receiver.getIban() == iban && amount <= this.getBalance()){
            balance -= amount;
            receiver.setBalance(receiver.getBalance()+amount);
            receiver.addTransaction("GOT: " + amount);
            addTransaction("SENT: " + amount);
        }
    }

    public void displayAccountInfo(){
        System.out.println("(" + ownerName + ", " + iban + ", " + balance + ")");
    }
}
