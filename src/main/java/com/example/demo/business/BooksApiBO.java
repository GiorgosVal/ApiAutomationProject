package com.example.demo.business;

import com.example.demo.apiclients.IRestAssuredClient;
import com.example.demo.dtos.BookDTO;
import com.example.demo.utils.BooksUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.testng.asserts.SoftAssert;

@Component
public class BooksApiBO extends GenericApiBO<BookDTO> {

    public BooksApiBO(@Qualifier("booksClient") IRestAssuredClient client) {
        super(client);
    }

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a list of the {@link BookDTO}
     */
    @Override
    String getAllSchema() {
        return "jsonschemas/books_list_json_schema.json";
    }

    /**
     * @return The path of the JSON schema that will be used to verify responses that contain a single {@link BookDTO}
     */
    @Override
    String getSingleSchema() {
        return "jsonschemas/book_single_json_schema.json";
    }

    /**
     * Verifies that the two objects are equal
     *
     * @param actual   The object to verify against the expected
     * @param expected The object that will be used as reference
     */
    @Override
    protected void verifyDto(BookDTO actual, BookDTO expected) {
        BookDTO expectedBook = BooksUtils.getExcpectedBookDTO(expected);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual.id, expectedBook.id, "Wrong book id");
        softAssert.assertEquals(actual.title, expectedBook.title, "Wrong book title");
        softAssert.assertEquals(actual.description, expectedBook.description, "Wrong book description");
        softAssert.assertEquals(actual.pageCount, expectedBook.pageCount, "Wrong book pageCount");
        softAssert.assertEquals(actual.excerpt, expectedBook.excerpt, "Wrong book excerpt");
        softAssert.assertEquals(actual.publishDate, expectedBook.publishDate, "Wrong book publishDate");
        softAssert.assertAll();
    }

    /**
     * See {@link BookDTO#dummyBook()} ()}
     *
     * @return same as the aforementioned method
     */
    @Override
    protected BookDTO dummyDto() {
        return BookDTO.dummyBook();
    }

    /**
     * See {@link BooksUtils#getExpectedErrorMessage(BookDTO)}
     *
     * @param bookDTO the dto to pass at the util
     * @return same as the util method
     */
    @Override
    ImmutablePair<String, String> getExpectedErrorMessage(BookDTO bookDTO) {
        return BooksUtils.getExpectedErrorMessage(bookDTO);
    }
}
