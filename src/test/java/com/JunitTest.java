package com;

import org.junit.*;

/**
 * @Author: qushiwen
 * @Date: 18-12-21
 * @version: 1.0
 */
public class JunitTest {
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("before class");
    }

    @Before
    public void testBefore() {
        System.out.println("before method");
    }

    @Test
    public void testJunit() {
        System.out.println("test junit");
    }

    @Test
    public void testJunit1() {
        System.out.println("test junit1");
    }

    @After
    public void testAfter() {
        System.out.println("after method");
    }

    @AfterClass
    public static void testAfterClass() {
        System.out.println("after class");
    }

}
