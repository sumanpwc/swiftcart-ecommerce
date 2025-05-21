package com.swiftcart.commom.dto;

import java.time.OffsetDateTime;

public class ResponseFactory {

	// success method
	
    public static <T> ApiResponse<T> success(String message, T payload, int count, String requestId) {
        ApiResponse.Data<T> data = new ApiResponse.Data<>(payload, count);
        return new ApiResponse<>(
                "success",
                200,
                message,
                OffsetDateTime.now().toString(),
                requestId,
                data
        );
    }

    // error method
    
    public static <T> ApiResponse<T> error(int code, String message, String requestId) {
        return new ApiResponse<>(
                "error",
                code,
                message,
                OffsetDateTime.now().toString(),
                requestId,
                null
        );
    }
}
