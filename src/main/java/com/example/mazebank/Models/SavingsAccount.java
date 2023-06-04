package com.example.mazebank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccount extends Account{
    // Betrag welcher der User vom SavingsAccount abheben kann
    private DoubleProperty withdrawalLimit;


    public SavingsAccount(String owner, String aNumber, double balance, double limit) {
        super(owner, aNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(this,"Withdrawal Limit",limit);
    }

    public DoubleProperty withdrawalLimitProperty() {
        return withdrawalLimit;
    }

    @Override
    public String toString(){
        return accountNumberProperty().get();
    }
}
