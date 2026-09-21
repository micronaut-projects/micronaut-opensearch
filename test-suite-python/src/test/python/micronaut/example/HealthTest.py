from typing import Annotated

from jakarta.inject import Inject
from micronaut.context.annotation import Property
from micronaut.http.client import HttpClient
from micronaut.http.client.annotation import Client
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test


# The OpenSearch container address is supplied by micronaut.example.support.OpenSearchTestConfigurer for the
# "opensearch" environment
@Property(name="endpoints.health.details-visible", value="anonymous")
@Property(name="micronaut.http.client.read-timeout", value="2M")
@MicronautTest(environments=["opensearch"])
class HealthTest:
    http_client: Annotated[HttpClient, Inject, Client("/")]

    @Test
    def test_health(self) -> None:
        client = self.http_client.toBlocking()
        json = client.retrieve("/health")
        assert "opensearch" in json
