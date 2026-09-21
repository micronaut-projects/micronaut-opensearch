from typing import Annotated

from jakarta.inject import Inject
from micronaut.context.annotation import Property
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test
from org.opensearch.client.transport import OpenSearchTransport
from org.opensearch.client.transport.aws import AwsSdk2Transport

from micronaut.example.aws.AwsSdk2TransportOptionsBeanCreatedEventListener import (
    AwsSdk2TransportOptionsBeanCreatedEventListener,
)


@Property(name="micronaut.opensearch.aws.enabled", value="true")
@Property(name="micronaut.opensearch.aws.endpoint", value="search-micronautguide-2abc3a4ab4s2cabc2r2vmbja.aos.us-east-1.on.aws")
@Property(name="micronaut.opensearch.aws.signing-region", value="us-east-1")
@Property(name="micronaut.opensearch.rest-client.enabled", value="false")
@MicronautTest(startApplication=False)
class AwsSdk2TransportOptionsBeanCreatedEventListenerTest:
    transport: Annotated[OpenSearchTransport, Inject]
    listener: Annotated[AwsSdk2TransportOptionsBeanCreatedEventListener, Inject]

    @Test
    def test_listener_customizes_the_transport_options(self) -> None:
        assert isinstance(self.transport, AwsSdk2Transport)
        assert self.listener.invoked
