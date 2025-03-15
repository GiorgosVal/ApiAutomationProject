package com.example.demo.dataproviders;

import com.example.demo.dtos.AuthorDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

/**
 * Data provider for the Author endpoint tests
 */
public class AuthorsDataProvider {

    /**
     * Generates valid instances of the {@link AuthorDTO}
     *
     * @return Instances of the {@link AuthorDTO} to be feed the tests
     */
    @DataProvider(name = "validAuthors")
    public static Object[][] getValidAuthors() {
        return new Object[][]{
                {AuthorDTO.dummyAuthor().withId(Integer.MAX_VALUE).withIdBook(Integer.MAX_VALUE).withFirstName("").withLastName("")},
                {AuthorDTO.dummyAuthor().withId(Integer.MIN_VALUE).withIdBook(Integer.MIN_VALUE)},
                {AuthorDTO.dummyAuthor().withId(0).withIdBook(0).withFirstName(null).withLastName(null)},
                {AuthorDTO.dummyAuthor().withId(null).withIdBook(null).withFirstName(null).withLastName(null)}};
    }

    /**
     * Generates invalid instances of the {@link AuthorDTO}
     *
     * @return Instances of the {@link AuthorDTO} to be feed the tests
     */
    @DataProvider(name = "invalidAuthors")
    public static Object[][] getInvalidAuthors() {
        JsonObject jsonObject = new Gson().fromJson("{\"property\" : \"value\"}", JsonObject.class);
        JsonArray jsonArray = new Gson().fromJson("[\"something\"]", JsonArray.class);
        return new Object[][]{
                //id
                {AuthorDTO.dummyAuthor().withId((long) Integer.MAX_VALUE + 1)},
                {AuthorDTO.dummyAuthor().withId((long) Integer.MIN_VALUE - 1)},
                {AuthorDTO.dummyAuthor().withId("something")},
                {AuthorDTO.dummyAuthor().withId("12")},
                {AuthorDTO.dummyAuthor().withId(true)},
                {AuthorDTO.dummyAuthor().withId(1.5)},
                {AuthorDTO.dummyAuthor().withId(jsonObject)},
                {AuthorDTO.dummyAuthor().withId(jsonArray)},
                //pageCount
                {AuthorDTO.dummyAuthor().withIdBook((long) Integer.MAX_VALUE + 1)},
                {AuthorDTO.dummyAuthor().withIdBook((long) Integer.MIN_VALUE - 1)},
                {AuthorDTO.dummyAuthor().withIdBook("something")},
                {AuthorDTO.dummyAuthor().withIdBook("12")},
                {AuthorDTO.dummyAuthor().withIdBook(true)},
                {AuthorDTO.dummyAuthor().withIdBook(1.5)},
                {AuthorDTO.dummyAuthor().withIdBook(jsonObject)},
                {AuthorDTO.dummyAuthor().withIdBook(jsonArray)},
                //title
                {AuthorDTO.dummyAuthor().withFirstName(1)},
                {AuthorDTO.dummyAuthor().withFirstName(true)},
                {AuthorDTO.dummyAuthor().withFirstName(jsonObject)},
                {AuthorDTO.dummyAuthor().withFirstName(jsonArray)},
                //description
                {AuthorDTO.dummyAuthor().withLastName(1)},
                {AuthorDTO.dummyAuthor().withLastName(true)},
                {AuthorDTO.dummyAuthor().withLastName(jsonObject)},
                {AuthorDTO.dummyAuthor().withLastName(jsonArray)},
                //empty body
                {null},
        };
    }
}
