package micronaut.example.aws

//tag::startclazz[]
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import jakarta.inject.Singleton
import org.opensearch.client.transport.aws.AwsSdk2TransportOptions

@Singleton
class AwsSdk2TransportOptionsBeanCreatedEventListener : BeanCreatedEventListener<AwsSdk2TransportOptions.Builder> {
//end::startclazz[]
    var invoked: Boolean = false
        private set
//tag::method[]
    override fun onCreated(event: BeanCreatedEvent<AwsSdk2TransportOptions.Builder>): AwsSdk2TransportOptions.Builder {
        val builder = event.bean
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
