package io.micronaut.opensearch.httpclient5.converters;

import org.apache.hc.core5.http.HttpHost;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringToHttpHostConverterTest {

    @Test
    void stringToHttpHost() {
        StringToHttpHostConverter converter = new StringToHttpHostConverter();
        Optional<HttpHost> convertOptional = converter.convert("http://localhost:9200", null, null);
        assertTrue(convertOptional.isPresent());
        HttpHost httpHost = convertOptional.get();
        assertEquals("localhost", httpHost.getHostName());
        assertEquals(9200, httpHost.getPort());
        assertEquals("http", httpHost.getSchemeName());
    }
}