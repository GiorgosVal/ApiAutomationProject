package com.example.demo.dtos;

import lombok.*;

/**
 * Dta Transfer Object for the Books endpoint.<br>
 * Used at the following cases:
 * <ol>
 *     <li>GET /api/v1/Books/ - Response (list)</li>
 *     <li>GET /api/v1/Books/{id} - Response</li>
 *     <li>POST /api/v1/Books - Request</li>
 *     <li>PUT /api/v1/Books/{id} - Request</li>
 * </ol>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class BookDTO extends AbstractApiDTO {

    //We define properties as Object to be able to perform negative test cases
    public Object id;
    public Object title;
    public Object description;
    public Object pageCount;
    public Object excerpt;
    public Object publishDate;

    /**
     * Constructs an instance of the DTO with valid values
     *
     * @return an instance of te DTO
     */
    public static BookDTO dummyBook() {
        return BookDTO.builder()
                .id(123)
                .title("Dummy title 1234567890-=!@#$%^&*()_+[]\\{}|;':\",./<>?}`~")
                .description("Dummy description 1234567890-=!@#$%^&*()_+[]\\{}|;':\",./<>?}`~")
                .pageCount(123)
                .excerpt("Dummy excerpt 1234567890-=!@#$%^&*()_+[]\\{}|;':\",./<>?}`~")
                .publishDate("2020-11-13T11:38:27.22926+00:00")
                .build();
    }
}