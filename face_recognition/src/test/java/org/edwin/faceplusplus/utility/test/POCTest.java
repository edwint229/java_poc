package org.edwin.faceplusplus.utility.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class POCTest {

    @Test
    public void testGenToken() {
        String token = RandomStringUtils.randomAlphanumeric(32);
        System.out.println(token);
    }
}
