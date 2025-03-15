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
@Story(AUTHORS_API_GET_BY_ID)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class GetAuthorByIdHappyTest extends TestsBase {

    @Autowired
    public AuthorsApiBO authorsApiBO;

    @Test(testName = "GET author by id: Verify a author can be retrieved and response matches JSON schema",
            groups = {REGRESSION, SMOKE, HAPPY_PATH, AUTHORS_API, AUTHORS_API_GET_BY_ID})
    @Description("GET author by id: Verify a author can be retrieved and response matches JSON schema")
    public void step1() {
        authorsApiBO.getByIdAndVerifyResponseCodeAndJsonSchema("1", AuthorDTO.class);
    }
}
