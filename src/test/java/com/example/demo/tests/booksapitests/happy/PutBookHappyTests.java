package com.example.demo.tests.booksapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
import com.example.demo.dataproviders.BooksDataProvider;
import com.example.demo.dtos.BookDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.example.demo.constants.TestTags.*;

@Epic(FAKE_REST_API)
@Feature(BOOKS_API)
@Story(BOOKS_API_PUT)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class PutBookHappyTests extends TestsBase {

    @Autowired
    public BooksApiBO booksApiBo;

    @Test(testName = "PUT book: Verify that an updated Book is accepted and response body is correct",
            dataProvider = "validBooks", dataProviderClass = BooksDataProvider.class,
            groups = {REGRESSION, SMOKE, HAPPY_PATH, BOOKS_API, BOOKS_API_PUT})
    @Description("PUT book: Verify that an updated Book is accepted and response body is correct")
    public void testStep1(BookDTO bookToCreate) {
        booksApiBo.putAndVerifyResponseCodeJsonSchemaAndBody("1", bookToCreate, BookDTO.class);
    }
}
