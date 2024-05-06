package com.mtech.qmsquestion.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaskUtilTest {

    @Test
    public void testMaskUtil_AbleToMaskResponse() {
        String input = "{ \"name\": \"Test123\", \"email\": \"test@test.com\" }";

        String maskedValue = MaskUtil.applyMask(input);

        String expectedValue = "{\"name\":\"*******\",\"email\":\"*************\"}";

        Assertions.assertEquals(expectedValue, maskedValue);
    }
}
