package io.micronaut.opensearch.aws;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringToRegionConverterTest {

    @Test
    void stringToRegionConverter() {
        new StringToRegionConverter();
        Optional<Region> regionOptional = new StringToRegionConverter().convert("us-east-1", Region.class, null);
        assertTrue(regionOptional.isPresent());
        Region region = regionOptional.get();
        assertEquals(Region.US_EAST_1, region);
    }
}