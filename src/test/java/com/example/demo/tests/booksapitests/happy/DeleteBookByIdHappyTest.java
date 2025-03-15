package com.example.demo.tests.booksapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
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
@Story(BOOKS_API_DELETE)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class DeleteBookByIdHappyTest extends TestsBase {

    @Autowired
    public BooksApiBO booksApiBO;

    @Test(testName = "DELETE book by id: Verify a book can be deleted and response body is empty",
            groups = {REGRESSION, SMOKE, HAPPY_PATH, BOOKS_API, BOOKS_API_DELETE})
    @Description("DELETE book by id: Verify a book can be deleted and response body is empty")
    public void step1() {
        booksApiBO.deleteByIdAndVerifyResponseCode("1");
    }
}
