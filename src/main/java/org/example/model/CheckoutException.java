package org.example.model;

public class CheckoutException extends RuntimeException{
    private String message;
    public CheckoutException(String message){
        this.message=message;
    }
}
