package org.jmock.poc.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.poc.Publisher;
import org.jmock.poc.Subscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PublisherTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void oneSubscriberReceivesAMessage() {
        // set up
        final Subscriber subscriber = context.mock(Subscriber.class);

        Publisher publisher = new Publisher();
        publisher.add(subscriber);

        final String message = "message";

        // expectations
        context.checking(new Expectations() {

            {
                oneOf(subscriber).receive(message);
                will(returnValue(message + "test"));
            }
        });

        // execute
        publisher.publish(message);
    }

}
