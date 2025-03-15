package com.example.demo.business;

import com.example.demo.apiclients.IRestAssuredClient;
import com.example.demo.dtos.AuthorDTO;
import com.example.demo.utils.AuthorsUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.testng.asserts.SoftAssert;

/**
 * Concrete Business Object for the Authors endpoint
 */
@Component
public class AuthorsApiBO extends GenericApiBO<AuthorDTO> {

    @Autowired
    public AuthorsApiBO(@Qualifier("authorsClient") IRestAssuredClient client) {
        super(client);
    }

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a list of the {@link AuthorDTO}
     */
    @Override
    String getAllSchema() {
        return "jsonschemas/authors_list_json_schema.json";
    }

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a single {@link AuthorDTO}
     */
    @Override
    String getSingleSchema() {
        return "jsonschemas/author_single_json_schema.json";
    }


    /**
     * Verifies that the two objects are equal
     *
     * @param actual   The object to verify against the expected
     * @param expected The object that will be used as reference
     */
    @Override
    protected void verifyDto(AuthorDTO actual, AuthorDTO expected) {
        AuthorDTO expectedAuthor = AuthorsUtils.getExcpectedAuthorDTO(expected);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual.id, expectedAuthor.id, "Wrong author id");
        softAssert.assertEquals(actual.idBook, expectedAuthor.idBook, "Wrong author idBook");
        softAssert.assertEquals(actual.firstName, expectedAuthor.firstName, "Wrong author firstName");
        softAssert.assertEquals(actual.lastName, expectedAuthor.lastName, "Wrong author lastName");
        softAssert.assertAll();
    }

    /**
     * See {@link AuthorDTO#dummyAuthor()}
     *
     * @return same as the aforementioned method
     */
    @Override
    protected AuthorDTO dummyDto() {
        return AuthorDTO.dummyAuthor();
    }

    /**
     * See {@link AuthorsUtils#getExpectedErrorMessage(AuthorDTO)}
     *
     * @param authorDTO the dto to pass at the util
     * @return same as the util method
     */
    @Override
    ImmutablePair<String, String> getExpectedErrorMessage(AuthorDTO authorDTO) {
        return AuthorsUtils.getExpectedErrorMessage(authorDTO);
    }
}
