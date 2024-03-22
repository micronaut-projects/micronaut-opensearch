package io.micronaut.opensearch.health;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import io.micronaut.core.util.StringUtils;
import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthResult;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.opensearch.client.opensearch.cluster.HealthResponse;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class OpenSearchClientHealthIndicatorTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchHealthIndicatorConfigurationExistsByDefault() {
        assertTrue(beanContext.containsBean(OpenSearchHealthIndicatorConfiguration.class));
    }

    @Test
    void beanOfTypeOpenSearchHealthIndicatorConfigurationCanBeDisabled() {
        try (ApplicationContext ctx = ApplicationContext.run(Collections.singletonMap("endpoints.health.opensearch.enabled", StringUtils.FALSE))) {
            assertFalse(ctx.containsBean(OpenSearchHealthIndicatorConfiguration.class));
        }
    }

    @Test
    void healthResult() {
        HealthResponse healthResponse = healthResponse(true);
        HealthResult healthResult = OpenSearchClientHealthIndicator.healthResult(healthResponse, null);
        assertEquals(HealthStatus.UP, healthResult.getStatus());

        healthResponse = healthResponse(false);
        healthResult = OpenSearchClientHealthIndicator.healthResult(healthResponse, null);
        assertEquals(HealthStatus.DOWN, healthResult.getStatus());

        healthResponse = healthResponse(true);
        healthResult = OpenSearchClientHealthIndicator.healthResult(healthResponse, new RuntimeException("Buuuuuug"));
        assertEquals(HealthStatus.DOWN, healthResult.getStatus());
    }

    private HealthResponse healthResponse(boolean up) {
        return new HealthResponse.Builder()
                .initializingShards(0)
                .unassignedShards(0)
                .delayedUnassignedShards(0)
                .clusterName("docker-cluster")
                .status(up ? org.opensearch.client.opensearch._types.HealthStatus.Green : org.opensearch.client.opensearch._types.HealthStatus.Red)
                .timedOut(false)
                .activeShardsPercentAsNumber("100")
                .relocatingShards(0)
                .activePrimaryShards(2)
                .activeShards(2)
                .numberOfNodes(1)
                .numberOfDataNodes(1)
                .numberOfPendingTasks(0)
                .numberOfInFlightFetch(0)
                .taskMaxWaitingInQueueMillis("0")
                .build();
    }
}
