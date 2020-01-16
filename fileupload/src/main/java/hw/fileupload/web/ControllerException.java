package hw.fileupload.web;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleSqlSameNameException(SQLIntegrityConstraintViolationException ex){
		return new ResponseEntity<String>("Same Document name, please use another name",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullException(NullPointerException ex){
		return new ResponseEntity<String>("Null Value found ="+ex+"",HttpStatus.BAD_REQUEST);
	}
}
