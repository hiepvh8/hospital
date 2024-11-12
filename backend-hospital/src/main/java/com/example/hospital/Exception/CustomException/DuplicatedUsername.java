package com.example.hospital.Exception.CustomException;

public class DuplicatedUsername extends RuntimeException {
  public DuplicatedUsername(String message) {
    super(message);
  }
}
