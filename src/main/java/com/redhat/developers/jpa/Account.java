package com.redhat.developers.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.math.BigDecimal;
import java.util.Objects;

@Entity // indicates the POJO is a JPA entity
// Defines a named query to retrieve all accounts, and orders the result by account Number
// Another named query, this one finding accounts that match account Number
@NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM AccountPanache a ORDER BY a.accountNumber")
@NamedQuery(name = "Accounts.findByAccountNumber", query = "SELECT a FROM AccountPanache a WHERE a.accountNumber = :accountNumber ORDER BY a.accountNumber")
public class Account {

    /*
        When using JPA, the fields can be marked private instead of public
    */

    @Id // Tells JPA that the id field is the primary key of the database table
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountsSequence")
    private Long id;
    private Long accountNumber;
    private Long customerNumber;
    private String customerName;
    private BigDecimal balance;
    private AccountStatus accountStatus = AccountStatus.OPEN;

    public void markOverdrawn() {
        accountStatus = AccountStatus.OVERDRAWN;
    }

    public void removeOverdrawnStatus() {
        accountStatus = AccountStatus.OPEN;
    }

    public void close() {
        accountStatus = AccountStatus.CLOSED;
        balance = BigDecimal.valueOf(0);
    }

    public void withdrawFunds(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void addFunds(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(accountNumber, account.accountNumber) && Objects.equals(customerNumber, account.customerNumber) && Objects.equals(customerName, account.customerName) && Objects.equals(balance, account.balance) && accountStatus == account.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, customerNumber, customerName, balance, accountStatus);
    }

    @Override
    public String toString() {
        return "Account{id=%d, accountNumber=%d, customerNumber=%d, customerName='%s', balance=%s, accountStatus=%s}".formatted(id, accountNumber, customerNumber, customerName, balance, accountStatus);
    }
}
