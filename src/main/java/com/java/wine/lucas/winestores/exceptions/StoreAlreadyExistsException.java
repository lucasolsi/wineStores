package com.java.wine.lucas.winestores.exceptions;

public class StoreAlreadyExistsException extends RuntimeException
{
    public StoreAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
