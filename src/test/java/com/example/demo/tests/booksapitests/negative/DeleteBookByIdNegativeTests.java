package com.example.demo.tests.booksapitests.negative;

import com.example.demo.TestsBase;
import com.example.demo.business.BooksApiBO;
import com.example.demo.dataproviders.Int32DataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.example.demo.constants.TestTags.*;

@Epic(FAKE_REST_API)
@Feature(BOOKS_API)
@Story(BOOKS_API_DELETE)
@Tags(value = {@Tag(NEGATIVE_PATH), @Tag(REGRESSION), @Tag(BOOKS_API)})
public class DeleteBookByIdNegativeTests extends TestsBase {


    @Autowired
    private BooksApiBO booksApiBO;

    @Test(testName = "DELETE book by id: Verify not found error with non existing book id",
            dataProvider = "int32InsideBoundaryValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, BOOKS_API, BOOKS_API_DELETE})
    @Description("DELETE book by id: Verify not found error with non existing book id")
    @Issue("DELETE with non existing id returns 200 instead of 404 status code")
    public void step1(int id) {
        booksApiBO.verifyDeleteByIdNotFound(String.valueOf(id));
    }

    @Test(testName = "DELETE book by id: Verify invalid id error with invalid book id",
            dataProvider = "int32InvalidValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, BOOKS_API, BOOKS_API_DELETE})
    @Description("DELETE book by id: Verify invalid id error with invalid book id")
    public void step2(String id) {
        booksApiBO.verifyDeleteByIdNotValid(id);
    }


}
