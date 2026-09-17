package micronaut.example.aws

//tag::startclazz[]
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import jakarta.inject.Singleton
import org.opensearch.client.transport.aws.AwsSdk2TransportOptions

@Singleton
class AwsSdk2TransportOptionsBeanCreatedEventListener implements BeanCreatedEventListener<AwsSdk2TransportOptions.Builder> {
//end::startclazz[]
    boolean invoked
//tag::method[]
    @Override
    AwsSdk2TransportOptions.Builder onCreated(BeanCreatedEvent<AwsSdk2TransportOptions.Builder> event) {
        AwsSdk2TransportOptions.Builder builder = event.bean
//end::method[]
        invoked = true
//tag::endmethod[]
        // Modify the builder here
        return builder
    }
//end::endmethod[]
//tag::endclazz[]
}
//end::endclazz[]
