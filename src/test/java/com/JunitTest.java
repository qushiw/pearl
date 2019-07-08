package com;

import org.junit.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: qushiwen
 * @Date: 18-12-21
 * @version: 1.0
 */
public class JunitTest {


    public static void main(String[] args) {
        System.out.println();
    }



//    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("before class");
    }

//    @Before
    public void testBefore() {
        System.out.println("before method");
    }

    @Test
    public void testJunit() {
        try {
            String filePath = "D:\\temp\\test.txt";
            InputStream inputStream = getClass().getResourceAsStream("JunitTest.class");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine())!=null) {
                System.out.println(line);
            }

/*            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String lineData;
            while ((lineData = br.readLine()) != null) {
                System.out.println(lineData);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        System.out.println("print a");
    }


//    @Test
    public void testJunit1() {
        System.out.println("test junit1");
    }

//    @After
    public void testAfter() {
        System.out.println("after method");
    }

//    @AfterClass
    public static void testAfterClass() {
        System.out.println("after class");
    }

}
