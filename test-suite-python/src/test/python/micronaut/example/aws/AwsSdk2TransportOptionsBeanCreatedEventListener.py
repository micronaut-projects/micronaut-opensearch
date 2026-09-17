# tag::startclazz[]
from jakarta.inject import Singleton
from micronaut.context.event import BeanCreatedEvent, BeanCreatedEventListener
from org.opensearch.client.transport.aws import AwsSdk2TransportOptions


@Singleton
class AwsSdk2TransportOptionsBeanCreatedEventListener(BeanCreatedEventListener[AwsSdk2TransportOptions.Builder]):
# end::startclazz[]
    invoked: bool = False

# tag::method[]
    def onCreated(self, event: BeanCreatedEvent[AwsSdk2TransportOptions.Builder]) -> AwsSdk2TransportOptions.Builder:
        builder = event.getBean()
# end::method[]
        self.invoked = True
# tag::endmethod[]
        # Modify the builder here
        return builder
# end::endmethod[]
# tag::endclazz[]
# end::endclazz[]
