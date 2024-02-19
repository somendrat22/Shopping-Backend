package com.shopping.backend.user.User.API.Exceptions;


public class MailNotSendException extends RuntimeException {
    public MailNotSendException(String message){
        super(message);
    }
}
