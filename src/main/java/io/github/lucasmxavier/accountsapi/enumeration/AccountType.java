package io.github.lucasmxavier.accountsapi.enumeration;

import io.github.lucasmxavier.accountsapi.exceptions.ApplicationException;

public enum AccountType {

    CONTA_CORRENTE("CONTA-CORRENTE"), CONTA_POUPANCA("CONTA-POUPANCA"), RETURN("RETURN");

    private String value;

    private AccountType(String value) {
        this.value = value;
    }

    public static AccountType getEnum(String value) {
        for(AccountType t : values()) {
            if(value.equals(t.getValue())) {
                return t;
            }
        }

        throw new ApplicationException();
    }

    public String getValue() {
        return value;
    }

}
