package com.example.demo.tests.authorsapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.AuthorsApiBO;
import com.example.demo.dtos.AuthorDTO;
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
@Story(AUTHORS_API_GET_ALL)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class GetAllAuthorsHappyTest extends TestsBase {

    @Autowired
    private AuthorsApiBO authorsApiBO;

    @Test(testName = "GET all authors: Verify all authors can be retrieved and response matches JSON schema",
            groups = {REGRESSION, SMOKE, HAPPY_PATH, AUTHORS_API, AUTHORS_API_GET_ALL})
    @Description("GET all authors: Verify all authors can be retrieved and response matches JSON schema")
    public void step1() {
        authorsApiBO.getAllAndVerifyResponseCodeAndJsonSchema(AuthorDTO.class);
    }
}