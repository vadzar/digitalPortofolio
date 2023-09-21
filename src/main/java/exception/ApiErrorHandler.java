package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiErrorHandler {
	private final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
	private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

	@ExceptionHandler(value = {ApiNotFoundException.class})
	public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e, WebRequest request) {
		var notFoundException = new ApiCustomException(
				e.getMessage(),
				NOT_FOUND,
				ZonedDateTime.now(ZoneId.of("Z")),
				request.getDescription(false)
		);
		return new ResponseEntity<>(notFoundException, NOT_FOUND);
	}

	@ExceptionHandler(value = {ApiBadRequestException.class})
	public ResponseEntity<Object> handleApiBadRequestException(ApiBadRequestException e, WebRequest request) {
		var notFoundException = new ApiCustomException(
				e.getMessage(),
				BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z")),
				request.getDescription(false)
		);
		return new ResponseEntity<>(notFoundException, BAD_REQUEST);
	}
}
