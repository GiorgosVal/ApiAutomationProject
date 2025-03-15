package com.example.demo.dtos;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object for the error responses of the API<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class ErrorResponseDTO {

    private String type;
    private String title;
    private int status;
    private String traceId;
    private Map<String, List<String>> errors;
}
