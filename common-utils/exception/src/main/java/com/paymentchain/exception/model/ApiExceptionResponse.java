package com.paymentchain.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "This model is used to return errors in RFC 7807 which created a generalized error-handling schema composed by five parts")
public record ApiExceptionResponse(
    @Schema(description = "The unique uri identifier that categorizes the error", name = "type",
        requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized")
    String type,

    @Schema(description = "A brief, human-readable message about the error", name = "title",
        requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have autorization")
    String title,

    @Schema(description = "The unique error code", name = "code",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "192")
    String code,

    @Schema(description = "A human-readable explanation of the error", name = "detail",
        requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have the property permissions to access the "
                                                               + "resource, please contact with us https://sotobotero.com")
    String detail,

    @Schema(description = "A URI that identifies the specific occurrence of the error", name = "detail",
        requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized/01")
    String instance
) {
    public ApiExceptionResponse(String detail, String code, String title, String type) {
        this(type, title, code, detail, "");
    }


}
