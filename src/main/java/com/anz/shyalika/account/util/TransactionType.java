package com.anz.shyalika.account.util;

/**
 * Enum class used for Transaction type to match the Database column enum
 * 
 * @author Shyalika Benthotage
 */
public enum TransactionType {

    DEBIT("Debit"), CREDIT("Credit");

    private String name;

    private TransactionType(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of the enum
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }
}
