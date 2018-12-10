package com.bytebuddy;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */
public class MemoryDatabase {

    public List<String> load(String info) {
        return Arrays.asList(info + ": foo", info + ": bar");
    }
}
