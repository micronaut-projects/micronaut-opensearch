/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.opensearch.health;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import jakarta.inject.Singleton;
import org.opensearch.client.opensearch.OpenSearchAsyncClient;
import org.opensearch.client.opensearch.cluster.HealthResponse;
import org.reactivestreams.Publisher;

import java.io.IOException;
import java.util.Locale;

import static io.micronaut.health.HealthStatus.DOWN;
import static io.micronaut.health.HealthStatus.UP;

/**
 * Health indicator integration for the configured OpenSearch cluster. 
 */
@Singleton
public class OpenSearchClientHealthIndicator implements HealthIndicator {

    private static final String NAME = "opensearchclient";

    private final OpenSearchAsyncClient client;

    /**
     * Constructor.
     *
     * @param client The OpenSearch high level REST client.
     */
    public OpenSearchClientHealthIndicator(OpenSearchAsyncClient client) {
        this.client = client;
    }

    /**
     * Tries to call the cluster info API on OpenSearch to obtain information about
     * the cluster. If the call succeeds, the OpenSearch cluster health status
     * (GREEN / YELLOW / RED) will be included in the health indicator details.
     *
     * @return A positive health result UP if the cluster can be communicated with
     *         and is in either GREEN or YELLOW status. A negative health result
     *         DOWN if the cluster cannot be communicated with or is in RED status.
     */
    @Override
    public Publisher<HealthResult> getResult() {
        return (subscriber -> {
            try {
                client.cluster().health().handle((health, exception) -> {
                    subscriber.onNext(healthResult(health, exception));
                    subscriber.onComplete();
                    return health;
                });
            } catch (IOException ex) {
                subscriber.onNext(healthResult(null, ex));
                subscriber.onComplete();
            }
        });
    }

    /**
     *
     * @param healthResponse HealthResponse
     * @param exception Exception
     * @return Health Result
     */
    static HealthResult healthResult(@Nullable HealthResponse healthResponse, @Nullable Throwable exception) {
        HealthResult.Builder resultBuilder = HealthResult.builder(NAME);
        if (exception != null) {
            return resultBuilder.status(DOWN).exception(exception).build();
        }
        if (healthResponse == null) {
            return resultBuilder.status(DOWN).build();
        }
        HealthStatus status = healthResponse.status() == org.opensearch.client.opensearch._types.HealthStatus.Red ? DOWN : UP;
        return resultBuilder.status(status).details(healthResultDetails(healthResponse)).build();
    }

    private static String healthResultDetails(HealthResponse response) {
        return "{" + "\"cluster_name\":\"" + response.clusterName() + "\"," + "\"status\":\""
                + response.status().name().toLowerCase(Locale.ENGLISH) + "\"," + "\"timed_out\":" + response.timedOut()
                + "," + "\"number_of_nodes\":" + response.numberOfNodes() + "," + "\"number_of_data_nodes\":"
                + response.numberOfDataNodes() + "," + "\"number_of_pending_tasks\":" + response.numberOfPendingTasks()
                + "," + "\"number_of_in_flight_fetch\":" + response.numberOfInFlightFetch() + ","
                + "\"task_max_waiting_in_queue\":\"" + response.taskMaxWaitingInQueueMillis() + "\","
                + "\"task_max_waiting_in_queue_millis\":" + response.taskMaxWaitingInQueueMillis() + ","
                + "\"active_shards_percent_as_number\":\"" + response.activeShardsPercentAsNumber() + "\","
                + "\"active_primary_shards\":" + response.activePrimaryShards() + "," + "\"active_shards\":"
                + response.activeShards() + "," + "\"relocating_shards\":" + response.relocatingShards() + ","
                + "\"initializing_shards\":" + response.initializingShards() + "," + "\"unassigned_shards\":"
                + response.unassignedShards() + "," + "\"delayed_unassigned_shards\":"
                + response.delayedUnassignedShards() + "}";
    }
}
