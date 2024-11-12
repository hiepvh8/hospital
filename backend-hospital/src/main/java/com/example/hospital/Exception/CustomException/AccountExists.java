package com.example.hospital.Exception.CustomException;

public class AccountExists extends RuntimeException {
  public AccountExists(String message) {
    super(message);
  }
}
