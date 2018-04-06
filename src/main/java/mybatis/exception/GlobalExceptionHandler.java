package mybatis.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public @ResponseBody ExceptionResponse handle403(AuthenticationException ae) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ae.getMessage());
        error.setStatus(403);
        return error;
    }

}
