package com.example.demo.tests.authorsapitests.happy;

import com.example.demo.TestsBase;
import com.example.demo.business.AuthorsApiBO;
import com.example.demo.dataproviders.AuthorsDataProvider;
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
@Story(AUTHORS_API_PUT)
@Tags(value = {@Tag(HAPPY_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class PutAuthorHappyTests extends TestsBase {

    @Autowired
    public AuthorsApiBO authorsApiBo;

    @Test(testName = "PUT author: Verify that an updated Author is accepted and response body is correct",
            dataProvider = "validAuthors", dataProviderClass = AuthorsDataProvider.class,
            groups = {REGRESSION, SMOKE, HAPPY_PATH, AUTHORS_API, AUTHORS_API_PUT})
    @Description("PUT author: Verify that an updated Author is accepted and response body is correct")
    public void testStep1(AuthorDTO authorToCreate) {
        authorsApiBo.putAndVerifyResponseCodeJsonSchemaAndBody("1", authorToCreate, AuthorDTO.class);
    }
}
