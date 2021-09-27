package io.github.lucasmxavier.accountsapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lucasmxavier.accountsapi.enumeration.AccountType;
import io.github.lucasmxavier.accountsapi.factory.AccountFactory;
import io.github.lucasmxavier.accountsapi.factory.impl.AccountFactoryImpl;
import io.github.lucasmxavier.accountsapi.model.Account;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private AccountFactory factory;
    private List<Account> accounts;

    public void createFactory() {
        if(factory == null) {
            factory = new AccountFactoryImpl();
        }
    }

    public void createAccountList() {
        if(accounts == null) {
            accounts = new ArrayList<>();
        }
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private long parseId(JSONObject account) {
        return Long.valueOf((int) account.get("id"));
    }

    private BigDecimal parseAmount(JSONObject account) {
        return new BigDecimal((String) account.get("amount"));
    }

    private void setAccountValues(JSONObject jsonAccount, Account account) throws Exception {
        String type = (String) jsonAccount.get("type");
        String number = (String) jsonAccount.get("number");
        String clientName = (String) jsonAccount.get("clientName");
        String agency = (String) jsonAccount.get("agency");

        account.setNumber(number != null ? number : account.getNumber());
        account.setClientName(clientName != null ? clientName : account.getClientName());
        account.setAgency(agency != null ? agency : account.getAgency());
        account.setAmount(jsonAccount.get("amount") != null ? parseAmount(jsonAccount) : account.getAmount());
        account.setType(type != null ? AccountType.getEnum(type) : account.getType());
    }

    public Account create(JSONObject jsonAccount) throws Exception {

        createFactory();

        Account account = factory.createAccount((String) jsonAccount.get("type"));
        account.setId(parseId(jsonAccount));
        setAccountValues(jsonAccount, account);

        return account;
    }

    public Account update(Account account, JSONObject jsonAccount) throws Exception {

        setAccountValues(jsonAccount, account);
        return account;
    }

    public void add(Account account) {
        createAccountList();
        accounts.add(account);
    }

    public List<Account> find() {
        createAccountList();
        return accounts;
    }

    public Account findById(long id) {
        return accounts.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
    }

    public void delete() {
        accounts.clear();
    }

    public void clearObjects() {
        accounts = null;
        factory = null;
    }

}
