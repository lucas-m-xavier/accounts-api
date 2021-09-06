package io.github.lucasmxavier.accountsapi.factory;

import io.github.lucasmxavier.accountsapi.model.Account;

public interface AccountFactory {

    Account createAccount (String type);

}
