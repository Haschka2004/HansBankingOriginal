package com.example.mazebank.Models;

import com.example.mazebank.Views.AccountType;
import com.example.mazebank.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Model {
    //Singelton Pattern
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;


    //Client Data Section
    private Client client;
    private boolean clientLoginSuccesFlag;
    private final ObservableList<Transaction>latestTransaction;
    private final ObservableList<Transaction>allTransaction;

    //Admin Data Section
    private boolean adminLodinSuccessFlag;
    private final ObservableList<Client> clients;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // Client Data Section
        this.clientLoginSuccesFlag = false;
        this.client = new Client("","","",null,null,null);
        this.latestTransaction = FXCollections.observableArrayList();
        this.allTransaction = FXCollections.observableArrayList();

        //Admin Data Section
        this.adminLodinSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        // wenn null heißt es wurde noch kein model erzeugt
        if(model == null){
            model = new Model();
        }
        return model;
    }
    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){
        return databaseDriver;
    }



    /**
     * Client Mehthod Section
     */
    public boolean getClientLoginSuccessFlag(){
        return this.clientLoginSuccesFlag;
    }
    public void setClientLoginSuccesFlag(boolean flag){
        this.clientLoginSuccesFlag = flag;
    }

    public Client getClient() {
        return client;
    }
    public  ObservableList getClients(){
        return clients;
    }
    public void evaluteClientCred(String pAddress, String password){
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress,password);

        try{
            if(resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                this.client.dateCreatedProperty().set(date);
                checkingAccount = getCheckingAccount(pAddress);
                savingsAccount = getSavingsAccount(pAddress);
                this.client.checkingAccountProperty().set(checkingAccount);
                this.client.savingsAccountProperty().set(savingsAccount);
                this.clientLoginSuccesFlag = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void prepareTransactions(ObservableList<Transaction> transactions, int limit){
        ResultSet resultSet = databaseDriver.getTransaction(this.client.payeeAddressProperty().get(),limit);
        try{
            while(resultSet.next()){
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver,amount,date,message));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setLatestTransaction(){
        prepareTransactions(this.latestTransaction,4);
    }

    public ObservableList<Transaction> getLatestTransaction(){
        return latestTransaction;
    }

    public void setAllTransaction(){
        prepareTransactions(this.allTransaction, -1);
    }

    public ObservableList<Transaction> getAllTransaction() {
        return allTransaction;
    }

    /**
     * Admin Method Section
     */
    public boolean getAdminLoginSuccessFlag(){
        return this.adminLodinSuccessFlag;
    }
    public void setAdminLodinSuccessFlag(boolean flag){
        adminLodinSuccessFlag = flag;
    }

    public void evaluatedAdminCred(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username,password);
        try{
            if(resultSet.isBeforeFirst()){
                this.adminLodinSuccessFlag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setClients(){
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getAllClientData();

        try{
            while(resultSet.next()){
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dataParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]),Integer.parseInt(dataParts[1]),Integer.parseInt(dataParts[2]));
                checkingAccount = getCheckingAccount(pAddress);
                savingsAccount = getSavingsAccount(pAddress);
                clients.add(new Client(fName,lName,pAddress,checkingAccount,savingsAccount,date));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<Client> searchClient(String pAddress){
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);

        try{
            CheckingAccount checkingAccount = getCheckingAccount(pAddress);
            SavingsAccount savingsAccount = getSavingsAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dataParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dataParts[0]),Integer.parseInt(dataParts[1]),Integer.parseInt(dataParts[2]));
            searchResults.add(new Client(fName,lName,pAddress,checkingAccount,savingsAccount,date));

        }catch(Exception e){
            e.printStackTrace();
        }
        return searchResults;
    }

    /**
     * Utility Methods Section
     */
    public CheckingAccount getCheckingAccount(String pAddress){
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int) resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new CheckingAccount(pAddress,num,balance,tLimit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public SavingsAccount getSavingsAccount(String pAddress){
        SavingsAccount account = null;
        ResultSet resultSet = databaseDriver.getSavingsAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            int wLimit = (int) resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SavingsAccount(pAddress,num,balance,wLimit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return account;
    }


}
