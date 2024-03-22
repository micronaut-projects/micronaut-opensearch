package io.micronaut.opensearch.restclient.converter;

import org.apache.http.Header;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
