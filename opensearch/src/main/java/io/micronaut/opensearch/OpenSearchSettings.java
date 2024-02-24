package io.micronaut.opensearch;

import org.apache.http.HttpHost;

public interface OpenSearchSettings {

	/**
	 * The prefix to use for all OpenSearch settings.
	 */
	String PREFIX = "opensearch";

	/**
	 * Default OpenSearch host.
	 */
	HttpHost DEFAULT_HOST = new HttpHost("127.0.0.1", 9200, "http");

}
