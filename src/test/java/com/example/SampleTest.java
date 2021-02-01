package com.example;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

//https://javacodehouse.com/blog/junit-tutorial/
//http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/CoreMatchers.html
public class SampleTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {


        //Method annotated with `@BeforeClass` will execute once before any of the test methods in this class.

        //This method could be used to set up any test fixtures that are computationally expensive and shared by several test methods. e.g. establishing database connections

        //Sometimes several tests need to share computationally expensive setup (like logging into a database). While this can compromise the independence of tests, sometimes it is a necessary optimization. From http://junit.sourceforge.net/javadoc/org/junit/BeforeClass.html

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        //Method annotated with `@AfterClass` will execute once after all of the test methods are executed in this class.

        //If you allocate expensive external resources in a BeforeClass method you need to release them after all the tests in the class have run. Annotating a public static void method with @AfterClass causes that method to be run after all the tests in the class have been run. All @AfterClass methods are guaranteed to run even if a BeforeClass method throws an exception. From http://junit.sourceforge.net/javadoc/org/junit/AfterClass.html
    }

    @Test
    public void testAssetThatExamples() {

        // 'theString' should contain 'S' and 'r'
        assertThat("theString", both(containsString("S")).and(containsString("r")));

        List<String> items = Arrays.asList("John", "James", "Julia", "Jim");

        // items list should have James and Jim
        assertThat(items, hasItems("James", "Jim"));

        // Every item in the list should have the character 'J'
        assertThat(items, everyItem(containsString("J")));

        // check all of the matchers
        assertThat("Once", allOf(equalTo("Once"), startsWith("O")));

        // negation of all of the matchers
        assertThat("Once", not(allOf(equalTo("test"), containsString("test"))));
    }

    @Before
    public void setUp() throws Exception {
        //Method annotated with `@Before` will execute before each test method in this class is executed.

        //If you find that several tests need similar objects created before they can run this method could be used to do set up those objects (aka test-fixtures).
    }

    @After
    public void tearDown() throws Exception {

        //Method annotated with `@After` will execute after each test method in this class is executed.

        //If you allocate external resources in a Before method you must release them in this method.
    }

    @Test
    public void test1() {

        //A public void method annotated with @Test will be executed as a test case.
    }

    @Test
    public void test2() {

        //Another test cases
    }

}