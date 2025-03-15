package com.example.demo.tests.booksapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
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
@Story(BOOKS_API_GET_ALL)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class GetAllBooksHappyTest extends TestsBase {

    @Autowired
    private BooksApiBO booksApiBO;

    @Test(testName = "GET all books: Verify all books can be retrieved and response matches JSON schema",
            groups = {REGRESSION, SMOKE, HAPPY_PATH, BOOKS_API, BOOKS_API_GET_ALL})
    @Description("GET all books: Verify all books can be retrieved and response matches JSON schema")
    public void step1() {
        booksApiBO.getAllAndVerifyResponseCodeAndJsonSchema(BookDTO.class);
    }
}