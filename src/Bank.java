public class Bank {

    private static double transferFeeRate;

    public static void setTransferFeeRate(double rate) {
        if(rate < 0) return;
        transferFeeRate = rate;
    }
    public static double getTransferFeeRate() {
        return transferFeeRate;
    }

    public static double calculateTransferFee(double amount) {
        return amount * transferFeeRate;
    }

}

