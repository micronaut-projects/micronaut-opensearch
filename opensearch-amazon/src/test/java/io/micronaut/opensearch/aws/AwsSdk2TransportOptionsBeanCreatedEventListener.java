package io.micronaut.opensearch.aws;

//tag::startclazz[]
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import jakarta.inject.Singleton;
import org.opensearch.client.transport.aws.AwsSdk2TransportOptions;

@Singleton
class AwsSdk2TransportOptionsBeanCreatedEventListener implements BeanCreatedEventListener<AwsSdk2TransportOptions.Builder> {
//end::startclazz[]
    private boolean invoked;
//tag::method[]
    @Override
    public AwsSdk2TransportOptions.Builder onCreated(BeanCreatedEvent<AwsSdk2TransportOptions.Builder> event) {
        AwsSdk2TransportOptions.Builder builder = event.getBean();
//end::method[]
        invoked = true;
//tag::endmethod[]
        // Modify the builder here
        return builder;
    }
//end::endmethod[]

    public boolean isInvoked() {
        return invoked;
    }
//tag::endclazz[]
}
//end::endclazz[]