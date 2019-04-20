package todolist

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @Throws(Exception::class)
    @ExceptionHandler(
            NoSuchElementException::class,
            EmptyResultDataAccessException::class,
            MethodArgumentTypeMismatchException::class
    )
    fun handleOtherException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        return when (ex) {
            // throw if not found data is accessed
            is NoSuchElementException -> {
                val status = HttpStatus.BAD_REQUEST
                val response = ErrorResponse(status.value(), ex.localizedMessage)
                super.handleExceptionInternal(ex, response, headers, status, request)
            }
            // throw if empty data is accessed
            is EmptyResultDataAccessException -> {
                val status = HttpStatus.BAD_REQUEST
                val response = ErrorResponse(status.value(), ex.localizedMessage)
                super.handleExceptionInternal(ex, response, headers, status, request)
            }
            // throw if api parameters type mismatch
            is MethodArgumentTypeMismatchException -> {
                val status = HttpStatus.BAD_REQUEST
                val response = ErrorResponse(status.value(), ex.localizedMessage)
                super.handleExceptionInternal(ex, response, headers, status, request)
            }
            else -> throw ex
        }
    }

    override fun handleExceptionInternal(ex: java.lang.Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        // すでにResponseEntityExceptionHandlerで定義済みのエラーをErrorResponseに変換
        val response = ErrorResponse(status.value(), status.reasonPhrase)
        return super.handleExceptionInternal(ex, response, headers, status, request)
    }
}
