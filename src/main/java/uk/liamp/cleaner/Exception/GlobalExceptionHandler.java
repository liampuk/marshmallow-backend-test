package uk.liamp.cleaner.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler class for the application.
 * @author liampuk
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handler for validation exceptions thrown while parsing JSON. As multiple validation
	 * errors can occur, a list of error messages is returned.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		List<String> errorMessages = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage())
				.collect(Collectors.toList());
		
		HashMap<String, Object> response = new HashMap<>();
		response.put("timestamp", new Date());
		response.put("status", "400");
		response.put("error", "Bad Request");
		response.put("messages", errorMessages);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Exception handler for invalid navigation exceptions, for use when the ship
	 * goes out of bounds or an invalid string is provided in the request.
	 */
	@ExceptionHandler(InvalidNavigationInstructionsException.class)
	public ResponseEntity<Object> handleInvalidNavigationInstructionsException(
			InvalidNavigationInstructionsException ex) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("timestamp", new Date());
		response.put("status", "400");
		response.put("error", "Bad Request");
		response.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}

}
