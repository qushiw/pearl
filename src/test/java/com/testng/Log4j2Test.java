package com.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @Author: qushiwen
 * @Date: 18-12-10
 * @version: 1.0
 */
public class Log4j2Test {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    public void test() {
        logger.info("test");
    }

}
