package com.example.demo.utils;

import com.example.demo.dtos.AuthorDTO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Utilities that help with the assertions of the {@link AuthorDTO}
 */
@UtilityClass
public class AuthorsUtils {

    /**
     * Sets the {@link AuthorDTO#id} and {@link AuthorDTO#idBook} to {@code 0} if they are {@code null}.
     *
     * @param authorDto The DTO to transform its aforementioned properties
     * @return The same DTO with transformed properties
     */
    public static AuthorDTO getExcpectedAuthorDTO(AuthorDTO authorDto) {
        if (authorDto.id == null) {
            authorDto.id = 0;
        }

        if (authorDto.idBook == null) {
            authorDto.idBook = 0;
        }
        return authorDto;
    }

    /**
     * Returns a key/value pair, where the key is expected to be found inside the errors map of the
     * {@link com.example.demo.dtos.ErrorResponseDTO}, and the value is the corresponding error message that
     * is expected to be found inside the list.
     * <br>
     * The pair is generated by examining which property inside the {@link AuthorDTO} has not a valid instance type
     * <br><br>
     * <b>throws</b> {@link Exception} if the method fails to identify properties with invalid type
     *
     * @param authorDto The DTO to examine its properties and generate the expected error
     * @return The expected key of the error and the error message that will exist inside the
     * {@link com.example.demo.dtos.ErrorResponseDTO}
     */
    @SneakyThrows
    public static ImmutablePair<String, String> getExpectedErrorMessage(AuthorDTO authorDto) {
        if (authorDto == null) {
            return new ImmutablePair<>("", "A non-empty request body is required.");
        }
        if (!(authorDto.id instanceof Integer)) {

            return new ImmutablePair<>("$.id", "The JSON value could not be converted to System.Int32. Path: $.id");

        }
        if (!(authorDto.idBook instanceof Integer)) {

            return new ImmutablePair<>("$.idBook", "The JSON value could not be converted to System.Int32. Path: $.idBook");

        }

        if (!(authorDto.firstName instanceof String)) {
            return new ImmutablePair<>("$.firstName", "The JSON value could not be converted to System.String. Path: $.firstName");
        }

        if (!(authorDto.lastName instanceof String)) {
            return new ImmutablePair<>("$.lastName", "The JSON value could not be converted to System.String. Path: $.lastName");
        }

        throw new Exception("Not implemented");
    }
}
