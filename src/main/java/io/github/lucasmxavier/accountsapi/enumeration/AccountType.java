package io.github.lucasmxavier.accountsapi.enumeration;

public enum AccountType {

    CONTA_CORRENTE("CONTA-CORRENTE"), CONTA_POUPANCA("CONTA-POUPANÇA"), RETURN("RETURN");

    private String value;

    private AccountType(String value) {
        this.value = value;
    }

    public static AccountType getEnum(String value) throws Exception {
        for(AccountType t : values()) {
            if(value.equals(t.getValue())) {
                return t;
            }
        }

        throw new Exception("Type not found.");
    }

    public String getValue() {
        return value;
    }

}
