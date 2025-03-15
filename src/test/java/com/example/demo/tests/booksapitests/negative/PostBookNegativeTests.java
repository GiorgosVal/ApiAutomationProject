package com.example.demo.tests.booksapitests.negative;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
import com.example.demo.dataproviders.BooksDataProvider;
import com.example.demo.dtos.BookDTO;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.example.demo.constants.TestTags.*;

@Epic(FAKE_REST_API)
@Feature(BOOKS_API)
@Story(BOOKS_API_POST)
@Tags(value = {@Tag(NEGATIVE_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class PostBookNegativeTests extends TestsBase {

    @Autowired
    public BooksApiBO booksApiBo;

    @Test(testName = "POST book: Verify error with invalid property",
            dataProvider = "invalidBooks", dataProviderClass = BooksDataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, BOOKS_API, BOOKS_API_POST})
    @Description("POST book: Verify error with invalid property")
    @Issue("String number is accepted in id and pageCount")
    @Issue("null publishDate is accepted")
    public void testStep1(BookDTO bookToCreate) {
        booksApiBo.verifyPostWithInvalidDto(bookToCreate);
    }
}
