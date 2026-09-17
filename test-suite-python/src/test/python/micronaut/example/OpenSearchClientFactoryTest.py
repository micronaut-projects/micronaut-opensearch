from typing import Annotated

from jakarta.inject import Inject
from micronaut.context import BeanProvider
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test
from org.opensearch.client.opensearch import OpenSearchAsyncClient, OpenSearchClient


@MicronautTest(startApplication=False)
class OpenSearchClientFactoryTest:
    client: Annotated[BeanProvider[OpenSearchClient], Inject]
    async_client: Annotated[BeanProvider[OpenSearchAsyncClient], Inject]

    @Test
    def test_bean_of_type_open_search_client_exists(self) -> None:
        assert self.client.isPresent()

    @Test
    def test_bean_of_type_open_search_async_client_exists(self) -> None:
        assert self.async_client.isPresent()
