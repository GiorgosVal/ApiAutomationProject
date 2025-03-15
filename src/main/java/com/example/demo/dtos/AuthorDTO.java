package com.example.demo.dtos;

import lombok.*;

/**
 * Dta Transfer Object for the Authors endpoint.<br>
 * Used at the following cases:
 * <ol>
 *     <li>GET /api/v1/Authors/ - Response (list)</li>
 *     <li>GET /api/v1/Authors/{id} - Response</li>
 *     <li>POST /api/v1/Authors - Request & Response</li>
 *     <li>PUT /api/v1/Authors/{id} - Request & Response</li>
 * </ol>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class AuthorDTO extends AbstractApiDTO {

    //We define properties as Object to be able to perform negative test cases
    public Object id;
    public Object idBook;
    public Object firstName;
    public Object lastName;

    /**
     * Constructs an instance of the DTO with valid values
     *
     * @return an instance of te DTO
     */
    public static AuthorDTO dummyAuthor() {
        return AuthorDTO.builder()
                .id(123)
                .firstName("Dummy name 1234567890-=!@#$%^&*()_+[]\\{}|;':\",./<>?}`~")
                .firstName("Dummy last name 1234567890-=!@#$%^&*()_+[]\\{}|;':\",./<>?}`~")
                .idBook(123)
                .build();
    }
}
