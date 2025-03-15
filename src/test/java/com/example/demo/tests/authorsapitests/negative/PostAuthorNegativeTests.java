package com.example.demo.tests.authorsapitests.negative;

import com.example.demo.TestsBase;
import com.example.demo.business.AuthorsApiBO;
import com.example.demo.dataproviders.AuthorsDataProvider;
import com.example.demo.dtos.AuthorDTO;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.example.demo.constants.TestTags.*;

@Epic(FAKE_REST_API)
@Feature(AUTHORS_API)
@Story(AUTHORS_API_POST)
@Tags(value = {@Tag(NEGATIVE_PATH), @Tag(REGRESSION), @Tag(AUTHORS_API)})
public class PostAuthorNegativeTests extends TestsBase {

    @Autowired
    public AuthorsApiBO authorsApiBo;

    @Test(testName = "POST author: Verify error with invalid property",
            dataProvider = "invalidAuthors", dataProviderClass = AuthorsDataProvider.class,
            groups = {REGRESSION, NEGATIVE_PATH, AUTHORS_API, AUTHORS_API_POST})
    @Description("POST author: Verify error with invalid property")
    @Issue("String number is accepted in id and idBook")
    public void testStep1(AuthorDTO authorToCreate) {
        authorsApiBo.verifyPostWithInvalidDto(authorToCreate);
    }
}
