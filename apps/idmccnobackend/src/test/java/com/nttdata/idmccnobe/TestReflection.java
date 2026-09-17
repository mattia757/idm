package com.nttdata.idmccnobe;

import java.lang.reflect.Field;

public final class TestReflection {

    private TestReflection() {
    }

    public static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
