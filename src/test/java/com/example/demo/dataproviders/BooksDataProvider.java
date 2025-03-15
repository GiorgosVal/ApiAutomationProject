package com.example.demo.dataproviders;

import com.example.demo.dtos.BookDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

/**
 * Data provider for the Books endpoint tests
 */
public class BooksDataProvider {

    /**
     * Generates valid instances of the {@link BookDTO}
     *
     * @return Instances of the {@link BookDTO} to be feed the tests
     */
    @DataProvider(name = "validBooks")
    public static Object[][] getValidBooks() {
        return new Object[][]{
                {BookDTO.dummyBook().withId(Integer.MAX_VALUE).withPageCount(Integer.MAX_VALUE).withTitle("").withDescription("").withExcerpt("")},
                {BookDTO.dummyBook().withId(Integer.MIN_VALUE).withPageCount(Integer.MIN_VALUE)},
                {BookDTO.dummyBook().withId(0).withPageCount(0).withTitle(null).withDescription(null).withExcerpt(null)},
                {BookDTO.dummyBook().withId(null).withPageCount(null).withTitle(null).withDescription(null).withExcerpt(null)}};
    }

    /**
     * Generates invalid instances of the {@link BookDTO}
     *
     * @return Instances of the {@link BookDTO} to be feed the tests
     */
    @DataProvider(name = "invalidBooks")
    public static Object[][] getInvalidBooks() {
        JsonObject jsonObject = new Gson().fromJson("{\"property\" : \"value\"}", JsonObject.class);
        JsonArray jsonArray = new Gson().fromJson("[\"something\"]", JsonArray.class);
        return new Object[][]{
                //id
                {BookDTO.dummyBook().withId((long) Integer.MAX_VALUE + 1)},
                {BookDTO.dummyBook().withId((long) Integer.MIN_VALUE - 1)},
                {BookDTO.dummyBook().withId("something")},
                {BookDTO.dummyBook().withId("12")},
                {BookDTO.dummyBook().withId(true)},
                {BookDTO.dummyBook().withId(1.5)},
                {BookDTO.dummyBook().withId(jsonObject)},
                {BookDTO.dummyBook().withId(jsonArray)},
                //pageCount
                {BookDTO.dummyBook().withPageCount((long) Integer.MAX_VALUE + 1)},
                {BookDTO.dummyBook().withPageCount((long) Integer.MIN_VALUE - 1)},
                {BookDTO.dummyBook().withPageCount("something")},
                {BookDTO.dummyBook().withPageCount("12")},
                {BookDTO.dummyBook().withPageCount(true)},
                {BookDTO.dummyBook().withPageCount(1.5)},
                {BookDTO.dummyBook().withPageCount(jsonObject)},
                {BookDTO.dummyBook().withPageCount(jsonArray)},
                //title
                {BookDTO.dummyBook().withTitle(1)},
                {BookDTO.dummyBook().withTitle(true)},
                {BookDTO.dummyBook().withTitle(jsonObject)},
                {BookDTO.dummyBook().withTitle(jsonArray)},
                //description
                {BookDTO.dummyBook().withDescription(1)},
                {BookDTO.dummyBook().withDescription(true)},
                {BookDTO.dummyBook().withDescription(jsonObject)},
                {BookDTO.dummyBook().withDescription(jsonArray)},
                //excerpt
                {BookDTO.dummyBook().withExcerpt(1)},
                {BookDTO.dummyBook().withExcerpt(true)},
                {BookDTO.dummyBook().withExcerpt(jsonObject)},
                {BookDTO.dummyBook().withExcerpt(jsonArray)},

                //publishDate
                {BookDTO.dummyBook().withPublishDate(null)},
                {BookDTO.dummyBook().withPublishDate("")},
                {BookDTO.dummyBook().withPublishDate("11-11-2020T11:38:27.22926+00:00")},
                {BookDTO.dummyBook().withPublishDate(1741858449)},
                {BookDTO.dummyBook().withPublishDate(false)},
                {BookDTO.dummyBook().withPublishDate(jsonObject)},
                {BookDTO.dummyBook().withPublishDate(jsonArray)},
                //empty body
                {null},
        };
    }
}
