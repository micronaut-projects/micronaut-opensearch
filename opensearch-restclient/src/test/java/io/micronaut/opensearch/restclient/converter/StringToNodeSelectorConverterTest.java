package io.micronaut.opensearch.restclient.converter;

import org.junit.jupiter.api.Test;
import org.opensearch.client.NodeSelector;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StringToNodeSelectorConverterTest {

    @Test
    void StringToNodeSelectorConvert() {
        StringToNodeSelectorConverter converter = new StringToNodeSelectorConverter();
        Optional<NodeSelector> nodeSelectorOptional = converter.convert("SKIP_DEDICATED_CLUSTER_MANAGERS", NodeSelector.class, null);
        assertTrue(nodeSelectorOptional.isPresent());
        assertEquals(NodeSelector.SKIP_DEDICATED_CLUSTER_MANAGERS, nodeSelectorOptional.get());
        nodeSelectorOptional = converter.convert("ANY", NodeSelector.class, null);
        assertTrue(nodeSelectorOptional.isPresent());
        assertEquals(NodeSelector.ANY, nodeSelectorOptional.get());
        nodeSelectorOptional = converter.convert("FOO", NodeSelector.class, null);
        assertFalse(nodeSelectorOptional.isPresent());
    }
}