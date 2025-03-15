package com.dct.nextgen.exception.handler;

import com.dct.nextgen.common.BaseCommon;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.exception.BaseAuthenticationException;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.exception.BaseException;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * Used to handle exceptions in the application centrally and return consistent responses <p>
 * Provides a standardized and centralized approach to handling common errors in Spring applications <p>
 * Helps log detailed errors, return structured responses, and easily internationalize error messages
 *
 * @author thoaidc
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private final BaseCommon baseCommon;

    public CustomExceptionHandler(BaseCommon baseCommon) {
        this.baseCommon = baseCommon;
        log.debug("ResponseEntityExceptionHandler 'CustomExceptionHandler' is configured for handle exception");
    }

    /**
     * Handle exceptions when an HTTP method is not supported (ex: calling POST on an endpoint that only supports GET)
     * @param e the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param request the current request
     * @return ResponseEntity with body is a BaseResponseDTO with custom message I18n
     */
    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(@Nullable HttpRequestMethodNotSupportedException e,
                                                                      @Nullable HttpHeaders headers,
                                                                      @Nullable HttpStatusCode status,
                                                                      @Nullable WebRequest request) {
        String message = Objects.nonNull(e) ? e.getMessage() : "";
        log.error("Handle method not allow exception. " + message);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.METHOD_NOT_ALLOWED,
            HttpStatusConstants.STATUS.FAILED,
            ExceptionConstants.METHOD_NOT_ALLOW,
            message
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle exceptions when a request is invalid due to data (validation error)<p>
     * For example: When using @{@link Valid} in a controller method and the incoming request data is invalid
     *
     * @param exception the exception to handle
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return ResponseEntity with body is a BaseResponseDTO with custom message I18n
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  @Nullable HttpHeaders headers,
                                                                  @Nullable HttpStatusCode status,
                                                                  @Nullable WebRequest request) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String errorKey = ExceptionConstants.INVALID_DATA; // Default message

        if (Objects.nonNull(fieldError))
            errorKey = fieldError.getDefaultMessage(); // If the field with an error includes a custom message key

        String reason = baseCommon.getMessageI18n(errorKey);
        log.error("Handle validate request data exception: {}. {}", reason, exception.getMessage());

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.BAD_REQUEST,
            HttpStatusConstants.STATUS.FAILED,
            errorKey
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BaseAuthenticationException.class })
    public ResponseEntity<BaseResponseDTO> handleBaseAuthenticationException(BaseAuthenticationException exception) {
        String reason = baseCommon.getMessageI18n(exception.getErrorKey(), exception.getArgs());
        log.error("[{}] Handle authentication exception: {}", exception.getEntityName(), reason, exception);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.UNAUTHORIZED,
            HttpStatusConstants.STATUS.FAILED,
            exception.getErrorKey()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ BaseBadRequestException.class })
    public ResponseEntity<BaseResponseDTO> handleBaseBadRequestException(BaseBadRequestException exception) {
        String reason = baseCommon.getMessageI18n(exception.getErrorKey(), exception.getArgs());
        log.error("[{}] Handle bad request alert exception: {}", exception.getEntityName(), reason, exception);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.BAD_REQUEST,
            HttpStatusConstants.STATUS.FAILED,
            exception.getErrorKey()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BaseException.class })
    public ResponseEntity<BaseResponseDTO> handleBaseException(BaseException exception) {
        String reason = baseCommon.getMessageI18n(exception.getErrorKey(), exception.getArgs());
        log.error("[{}] Handle exception: {}", exception.getEntityName(), reason, exception);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.BAD_REQUEST,
            HttpStatusConstants.STATUS.FAILED,
            exception.getErrorKey()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MaxUploadSizeExceededException.class })
    public ResponseEntity<Object> handleNullPointerException(MaxUploadSizeExceededException exception, WebRequest request) {
        log.error("[{}] Maximum upload size exceeded: {}", request.getClass().getName(), exception.getMessage());

        BaseResponseDTO responseDTO = new BaseResponseDTO(
                HttpStatusConstants.BAD_REQUEST,
                HttpStatusConstants.STATUS.FAILED,
                ExceptionConstants.MAXIMUM_UPLOAD_SIZE_EXCEEDED
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception, WebRequest request) {
        // Handle NullPointerException (include of Objects.requireNonNull())
        log.error("[{}] Null pointer exception occurred: {}", request.getClass().getName(), exception.getMessage());

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.INTERNAL_SERVER_ERROR,
            HttpStatusConstants.STATUS.FAILED,
            ExceptionConstants.NULL_EXCEPTION
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<BaseResponseDTO> handleRuntimeException(RuntimeException exception) {
        log.error("Handle runtime exception", exception);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.INTERNAL_SERVER_ERROR,
            HttpStatusConstants.STATUS.FAILED,
            ExceptionConstants.UNCERTAIN_ERROR
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<BaseResponseDTO> handleException(Exception exception) {
        log.error("Handle unexpected exception", exception);

        BaseResponseDTO responseDTO = new BaseResponseDTO(
            HttpStatusConstants.INTERNAL_SERVER_ERROR,
            HttpStatusConstants.STATUS.FAILED,
            ExceptionConstants.UNCERTAIN_ERROR
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
