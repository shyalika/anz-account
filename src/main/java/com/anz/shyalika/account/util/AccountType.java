package com.anz.shyalika.account.util;

/**
 * Enum class used for Account type to match the Database column enum
 * 
 * @author Shyalika Benthotage
 */
public enum AccountType {

    SAVINGS("Savings"), CURRENT("Current");

    private String name;

    private AccountType(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the enum
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }
}
