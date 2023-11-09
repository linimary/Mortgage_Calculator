package com.mortgage_calc;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance(short numOfPaymentsMade) {
        float monthlyInterest = getMonthlyInterest();
        short numOfPayments = getNumOfPayments();

        return principal
                * (Math.pow(1 + monthlyInterest, numOfPayments) - Math.pow(1 + monthlyInterest, numOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1);
    }

    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        short numOfPayments = getNumOfPayments();

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numOfPayments)
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1));
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month - 1] = calculateBalance(month);
        return balances;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }

    private short getNumOfPayments() {
        return (short) (years * MONTHS_IN_YEAR);
    }
}
