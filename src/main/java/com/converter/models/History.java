package com.converter.models;



public class History {
   private String valuateForChange;
    private  String target;
    private double amount;
    private double targetAmount;
    private String date;
    private long key;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public History()
    {

    }
    public History(String valuateForChange,String targetValuate,String amount,String targetAmount,String date,Long key){
            this.valuateForChange=valuateForChange;
            this.target=targetValuate;

            this.amount=Double.parseDouble(amount);
            this.targetAmount=Double.parseDouble(targetAmount);

            this.date=date;

            this.key=key;

    }

    public String getValuateForChange() {
        return valuateForChange;
    }

    public void setValuateForChange(String valuateForChange) {
        this.valuateForChange = valuateForChange;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
