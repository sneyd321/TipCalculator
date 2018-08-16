package com.example.ryan.tipcalculator;

/**
 * Created by Ryan on 8/15/2018.
 */

public class Tip {
    private final double HST = 1.13;

    private double amount;
    private double tipPercentage;
    private double numberOfPeople;
    private boolean hst;

    private double tipAmount;
    private double totalAmount;
    private double amountPerPerson;


    public Tip(){
        amount = 0;
        tipPercentage = 0;
        numberOfPeople = 0;
        hst = false;
        tipAmount = 0;
        totalAmount = 0;
        amountPerPerson = 0;
    }

    public void calculateTip(){
        if (this.hst) {
            this.tipAmount = this.amount * HST * (this.tipPercentage * .01);
            this.totalAmount = this.amount * HST + tipAmount;
            this.amountPerPerson = this.totalAmount / this.numberOfPeople;
        }
        else{
            this.tipAmount = this.amount * (this.tipPercentage * .01);
            this.totalAmount = this.amount + tipAmount;
            this.amountPerPerson = this.totalAmount / this.numberOfPeople;
        }

    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(double tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public double getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(double numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isHst() {
        return hst;
    }

    public void setHst(boolean hst) {
        this.hst = hst;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(int tipAmount) {
        this.tipAmount = tipAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountPerPerson() {
        return amountPerPerson;
    }

    public void setAmountPerPerson(int amountPerPerson) {
        this.amountPerPerson = amountPerPerson;
    }
}
