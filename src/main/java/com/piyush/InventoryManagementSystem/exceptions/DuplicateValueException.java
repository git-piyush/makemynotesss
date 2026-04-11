package com.piyush.InventoryManagementSystem.exceptions;

public class DuplicateValueException  extends RuntimeException{
    public DuplicateValueException(String message){
        super(message);
    }
}
