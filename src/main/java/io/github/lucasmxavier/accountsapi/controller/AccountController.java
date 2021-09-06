package io.github.lucasmxavier.accountsapi.controller;

import io.github.lucasmxavier.accountsapi.model.Account;
import io.github.lucasmxavier.accountsapi.service.AccountService;
import lombok.val;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api-accounts/accounts")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> find() {
        if(accountService.find().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(accountService.find());
        return ResponseEntity.ok(accountService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        try {
            accountService.delete();
            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Account> create(@RequestBody JSONObject account) {
        try {
            if(accountService.isJSONValid(account.toString())) {
                Account accountCreated = accountService.create(account);

                val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(accountCreated.getNumber()).build().toUri();

                accountService.add(accountCreated);
                return ResponseEntity.created(uri).body(null);
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e) {
            logger.error("JSON fields are not parsable. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<Account> update(@PathVariable("id") long id, @RequestBody JSONObject account) {
        try {
            if(accountService.isJSONValid(account.toString())) {
                Account tripToUpdate = accountService.findById(id);
                if(tripToUpdate == null){
                    logger.error("Account not found.");
                    return ResponseEntity.notFound().build();
                }else {
                    Account tripUpdated = accountService.update(tripToUpdate, account);
                    return ResponseEntity.ok(tripUpdated);
                }
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e) {
            logger.error("JSON fields are not parsable." + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

}
