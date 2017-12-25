package config.exceptionhandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
class ApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;


    public ApiError(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}