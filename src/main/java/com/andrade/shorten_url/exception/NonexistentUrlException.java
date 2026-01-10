package com.andrade.shorten_url.exception;

public class NonexistentUrlException extends Exception{
    public NonexistentUrlException(String message){
        super(message);
    }
}
