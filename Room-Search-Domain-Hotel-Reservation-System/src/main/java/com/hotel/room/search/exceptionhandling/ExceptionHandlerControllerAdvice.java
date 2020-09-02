package com.hotel.room.search.exceptionhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hotel.room.reservation.model.view.Response;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(RoomTypeNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response<RoomSearchViewResponse>> handleRoomType(final RoomTypeNotFoundException ex) {

		Response response = new Response();
		response.setMessage(ex.toString());
		response.setSuccess(false);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response<RoomSearchViewResponse>> handleValidation(final MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			FieldError fieldError = (FieldError) error;
			String fieldName = fieldError.getField();
			String errorMsg = fieldError.getDefaultMessage();
			errors.put(fieldName, errorMsg);
		});
		Response response = new Response();
		response.setMessage(ex.toString());
		response.setData(errors);
		response.setSuccess(false);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
