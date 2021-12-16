package com.ds.nofication.Models.Backend;

public class Dosage {
    private int amount;
    private Interval interval;
    private AmountType amountType;;
    public Dosage(int amount, AmountType amountType, Interval interval){
        this.amount = amount;
        this.amountType = amountType;
        this.interval = interval;
    }
    public int getAmount(){
        return this.amount;
    }
    public Interval getInterval(){
        return  this.interval;
    }

    public AmountType getAmountType() {
        return this.amountType;
    }
}
