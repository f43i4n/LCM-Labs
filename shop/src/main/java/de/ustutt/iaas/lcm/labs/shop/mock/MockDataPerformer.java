package de.ustutt.iaas.lcm.labs.shop.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MockDataPerformer implements ApplicationListener<ContextRefreshedEvent> {

    private MockInitializer mockInitializer;

    @Autowired
    public MockDataPerformer(MockInitializer mockInitializer) {
        this.mockInitializer = mockInitializer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        mockInitializer.perform();
    }
}
