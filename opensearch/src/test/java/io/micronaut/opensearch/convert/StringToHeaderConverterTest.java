package io.micronaut.opensearch.convert;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StringToHeaderConverterTest {

    @Test
    void stringToHeader() {
        StringToHeaderConverter converter = new StringToHeaderConverter();
        Optional<Header> convertOptional = converter.convert("Foo:Bar", null, null);
        assertTrue(convertOptional.isPresent());
        Header header = convertOptional.get();
        assertEquals("Foo", header.getName());
        assertEquals("Bar", header.getValue());
        assertFalse(converter.convert("FooBar", null, null).isPresent());
    }
}