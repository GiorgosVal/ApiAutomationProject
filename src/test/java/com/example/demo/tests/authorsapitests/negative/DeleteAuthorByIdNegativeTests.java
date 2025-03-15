package com.example.demo.tests.authorsapitests.negative;

import com.example.demo.TestsBase;
import com.example.demo.business.AuthorsApiBO;
import com.example.demo.dataproviders.Int32DataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.example.demo.constants.TestTags.*;

@Epic(FAKE_REST_API)
@Feature(AUTHORS_API)
@Story(AUTHORS_API_DELETE)
@Tags(value = {@Tag(NEGATIVE_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class DeleteAuthorByIdNegativeTests extends TestsBase {


    @Autowired
    private AuthorsApiBO authorsApiBO;

    @Test(testName = "DELETE author by id: Verify not found error with non existing author id",
            dataProvider = "int32InsideBoundaryValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, AUTHORS_API, AUTHORS_API_DELETE})
    @Description("DELETE author by id: Verify not found error with non existing author id")
    @Issue("DELETE with non existing id returns 200 instead of 404 status code")
    public void step1(int id) {
        authorsApiBO.verifyDeleteByIdNotFound(String.valueOf(id));
    }

    @Test(testName = "DELETE author by id: Verify invalid id error with invalid author id",
            dataProvider = "int32InvalidValues", dataProviderClass = Int32DataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, AUTHORS_API, AUTHORS_API_DELETE})
    @Description("DELETE author by id: Verify invalid id error with invalid author id")
    public void step2(String id) {
        authorsApiBO.verifyDeleteByIdNotValid(id);
    }


}
