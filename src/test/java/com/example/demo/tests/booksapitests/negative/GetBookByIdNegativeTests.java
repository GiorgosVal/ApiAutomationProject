package com.example.demo.tests.booksapitests.negative;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
import com.example.demo.dataproviders.Int32DataProvider;
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
@Story(BOOKS_API_GET_BY_ID)
@Tags(value = {@Tag(NEGATIVE_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class GetBookByIdNegativeTests extends TestsBase {


    @Autowired
    private BooksApiBO booksApiBO;

    @Test(testName = "GET book by id: Verify not found error with non existing book id",
            dataProvider = "int32InsideBoundaryValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, BOOKS_API, BOOKS_API_GET_BY_ID})
    @Description("GET book by id: Verify not found error with non existing book id")
    public void step1(int id) {
        booksApiBO.verifyGetByIdNotFound(String.valueOf(id));
    }

    @Test(testName = "GET book by id: Verify invalid id error with invalid book id",
            dataProvider = "int32InvalidValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, BOOKS_API, BOOKS_API_GET_BY_ID})
    @Description("GET book by id: Verify invalid id error with invalid book id")
    public void step2(String id) {
        booksApiBO.verifyGetByIdNotValid(id);
    }
}
