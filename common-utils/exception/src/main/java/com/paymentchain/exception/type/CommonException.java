package com.paymentchain.exception.type;

import com.paymentchain.exception.ApplicationException;
import com.paymentchain.exception.model.ApiExceptionResponse;
import com.paymentchain.exception.util.IErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class CommonException extends ApplicationException {
    // El constructor simplemente pasa los datos a la clase padre.
    // Es 'privado' para forzar el uso del enum como fábrica.
    private CommonException(IErrorType<CommonException> errorType, String finalDetail) {
        super(errorType, finalDetail);
    }

    /**
     * Enum que agrupa errores genéricos de la aplicación y actúa como fábrica.
     */
    @Getter
    public enum Type implements IErrorType<CommonException> {

        RESOURCE_NOT_FOUND(
            HttpStatus.NOT_FOUND, // 404
            "Recurso no Encontrado",
            "GEN-001",
            "El recurso que buscas no fue encontrado. ID: %s" // Usamos %s para formateo
        ),
        BAD_REQUEST(
            HttpStatus.BAD_REQUEST, // 400
            "Petición Inválida",
            "GEN-002",
            "La petición contiene datos inválidos o con formato incorrecto. Campo: %s"
        ),
        UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED, // 401
            "No Autorizado",
            "GEN-003",
            "No tienes permiso para acceder a este recurso. Token: %s"
        ),
        CANNOT_CONNECT_WITH_SERVICE(
            HttpStatus.SERVICE_UNAVAILABLE, // 503
            "No se puede conectar con el servicio",
            "GEN-005",
            "No se pudo establecer conexión con el servicio externo. Detalles: %s"
        ),
        GENERIC_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, // 500
            "Error Interno del Servidor",
            "GEN-004",
            "Ocurrió un error inesperado en el servidor. Detalles: %s"
        );

        private final HttpStatus httpStatus;
        private final String title;
        private final String code;
        private final String detail;

        Type(HttpStatus httpStatus, String title, String code, String detail) {
            this.httpStatus = httpStatus;
            this.title = title;
            this.code = code;
            this.detail = detail;
        }

        @Override
        public CommonException build() {
            // Llama al constructor privado con el mensaje de detalle por defecto.
            return new CommonException(this, this.detail);
        }

        @Override
        public CommonException build(Object... args) {
            // Llama al constructor privado con un mensaje formateado.
            return new CommonException(this, String.format(this.detail, args));
        }

        @Override
        public Supplier<CommonException> defer() {
            return () -> new CommonException(this, this.detail);
        }

        @Override
        public Supplier<CommonException> defer(Object... args) {
            return () -> new CommonException(this, String.format(this.detail, args));
        }

        @Override
        public String getType() {
            return this.name();
        }

        @Override
        public ApiExceptionResponse body(String message) {
            return new ApiExceptionResponse(
                this.getType(),
                this.getTitle(),
                this.getCode(),
                message
            );
        }
    }
}
