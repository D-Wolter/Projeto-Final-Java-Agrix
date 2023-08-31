package com.betrybe.agrix.dto;

/**
 * ResponseDTO.
 */
public record ResponseDto<T>(String message, T data) {
}