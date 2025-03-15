package com.example.demo;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * The test base of all tests, using the SpringBoot context
 */
@SpringBootTest(classes = DemoApplication.class)
public class TestsBase extends AbstractTestNGSpringContextTests {

}