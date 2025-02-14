package com.redhat.developers.entity;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix = "bank-support") // The prefix applies to all properties in the class.
public class BankSupportConfig {
    public String email; // bank-support.email
    private String phone; // bank-support.phone

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
