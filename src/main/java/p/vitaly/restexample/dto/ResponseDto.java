package p.vitaly.restexample.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Used for Create, Update, Delete and Error response
 */
@Data
public final class ResponseDto {
    private int code;
    private String description;
    private String message;

    public ResponseDto(HttpStatus httpStatus, String message) {
        code = httpStatus.value();
        description = httpStatus.getReasonPhrase();
        this.message = message;
    }
}
