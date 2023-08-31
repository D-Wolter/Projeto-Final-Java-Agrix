package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advice Exception.
 */
@ControllerAdvice
public class AdviceException {

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerException(FertilizerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }
}