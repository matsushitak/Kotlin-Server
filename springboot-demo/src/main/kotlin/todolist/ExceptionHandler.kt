package todolist

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @Throws(Exception::class)
    @ExceptionHandler(ApiException::class)
    fun handleOriginalException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        when (ex) {
            is ApiException -> {
                val status = HttpStatus.BAD_REQUEST
                return handleApiException(ex, headers, status, request)
            }
            else -> throw ex
        }
    }

    fun handleApiException(ex: ApiException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        println("APIエラー発生 : $ex")
        val response = ErrorResponse(status.value(), status.reasonPhrase)
        return super.handleExceptionInternal(ex, response, headers, status, request)
    }
}
