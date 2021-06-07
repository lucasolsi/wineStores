package com.java.wine.lucas.winestores.exceptions;

public class StoreNotFoundException extends RuntimeException
{
    public StoreNotFoundException(String msg)
    {
        super(msg);
    }
}
