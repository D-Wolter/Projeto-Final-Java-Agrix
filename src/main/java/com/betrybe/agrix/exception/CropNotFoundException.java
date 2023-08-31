package com.betrybe.agrix.exception;

/**
 *  CropNotFoundException.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }

}