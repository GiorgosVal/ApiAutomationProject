package com.example.demo.tests.authorsapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.AuthorsApiBO;
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
@Feature(AUTHORS_API)
@Story(AUTHORS_API_DELETE)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class DeleteAuthorByIdHappyTest extends TestsBase {

    @Autowired
    public AuthorsApiBO authorsApiBO;

    @Test(testName = "DELETE author by id: Verify an author can be deleted and response body is empty",
            groups = {REGRESSION, SMOKE, HAPPY_PATH, AUTHORS_API, AUTHORS_API_DELETE})
    @Description("DELETE author by id: Verify an author can be deleted and response body is empty")
    public void step1() {
        authorsApiBO.deleteByIdAndVerifyResponseCode("1");
    }
}
