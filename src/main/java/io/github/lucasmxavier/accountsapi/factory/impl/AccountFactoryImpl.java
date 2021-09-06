package io.github.lucasmxavier.accountsapi.factory.impl;

import io.github.lucasmxavier.accountsapi.enumeration.AccountType;
import io.github.lucasmxavier.accountsapi.factory.AccountFactory;
import io.github.lucasmxavier.accountsapi.model.Account;

public class AccountFactoryImpl implements AccountFactory {

    @Override
    public Account createAccount(String type) {
        if (AccountType.CONTA_CORRENTE.getValue().equals(type)) {
            return new Account(AccountType.CONTA_CORRENTE);
        } else if (AccountType.CONTA_POUPANÇA.getValue().equals(type)) {
            return new Account(AccountType.CONTA_POUPANÇA);
        }

        return new Account(AccountType.RETURN);
    }
}
