package com.example.mazebank.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {
    private final StringProperty sender;    //PayeeAddress
    private final StringProperty reiceiver;  //PayeeAddress
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    public Transaction(String sender, String reiceiver, double amount, LocalDate date,String message) {
        this.sender = new SimpleStringProperty(this,"Sender",sender);
        this.reiceiver = new SimpleStringProperty(this,"Receiver",reiceiver);
        this.amount = new SimpleDoubleProperty(this,"Amount",amount);
        this.date = new SimpleObjectProperty<>(this,"Date",date);
        this.message = new SimpleStringProperty(this,"Message",message);
    }

    public StringProperty senderProperty(){
       return this.sender;
    }

    public StringProperty reiceiverProperty() {
        return this.reiceiver;
    }

    public DoubleProperty amountProperty() {
        return this.amount;
    }


    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }


    public StringProperty messageProperty() {
        return this.message;
    }
}
