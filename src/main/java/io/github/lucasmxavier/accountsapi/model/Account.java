package io.github.lucasmxavier.accountsapi.model;

import io.github.lucasmxavier.accountsapi.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private String number;

    private String clientName;

    private String agency;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    public Account(AccountType type) {
        this.type = type;
    }
}
