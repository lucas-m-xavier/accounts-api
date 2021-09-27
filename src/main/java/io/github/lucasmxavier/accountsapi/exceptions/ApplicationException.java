package io.github.lucasmxavier.accountsapi.exceptions;

public class ApplicationException extends RuntimeException {

    @Override
    public String getMessage(){
        return "Type not found.";
    }

}
