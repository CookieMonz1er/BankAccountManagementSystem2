public class Demo {
    public static void main(String[] args) {
        Bank.setTransferFeeRate(0.2);

        BankAccount b1 = new BankAccount("Kutay", 12121212121L);
        BankAccount b2 = new BankAccount("Burhan", 22121212121L);

        b1.deposit(1000);
        b2.deposit(2000);



        b1.withdraw(500);
        b2.withdraw(500);



        b1.transferTo(b2, 200);


        b1.transferTo(b2, 1000);
        b1.displayHistory();
        b2.displayHistory();

        b1.displayAccountInfo();
        b2.displayAccountInfo();



    }
}
