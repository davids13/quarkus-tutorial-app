package com.redhat.developers.jpa.panache;

import com.redhat.developers.jpa.AccountStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class AccountPanache extends PanacheEntity { // Account extends PanacheEntity, which provides the data access helper methods like persist()
    public AccountStatus accountStatus = AccountStatus.OPEN;
    public Long accountNumber;
    public Long customerNumber;
    public String customerName;
    public BigDecimal balance;

    public static long totalAccountsForCustomer(Long customerNumber) {
        return find("customerNumber", customerNumber).count();
    }

    public static AccountPanache findByAccountNumber(Long accountNumber) {
        return find("accountNumber", accountNumber).firstResult();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountPanache accountPanache = (AccountPanache) o;
        return accountStatus == accountPanache.accountStatus && Objects.equals(accountNumber, accountPanache.accountNumber) && Objects.equals(customerNumber, accountPanache.customerNumber) && Objects.equals(customerName, accountPanache.customerName) && Objects.equals(balance, accountPanache.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountStatus, accountNumber, customerNumber, customerName, balance);
    }

    @Override
    public String toString() {
        return "Account{accountStatus=%s, accountNumber=%d, customerNumber=%d, customerName='%s', balance=%s}".formatted(accountStatus, accountNumber, customerNumber, customerName, balance);
    }
}
