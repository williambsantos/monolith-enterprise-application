/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.messaging.adapter;

import com.mycompany.entapp.snowman.infrastructure.messaging.InvoiceSystemPort;
import com.mycompany.entapp.snowman.infrastructure.messaging.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class InvoiceSystemAdapter implements InvoiceSystemPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceSystemAdapter.class);

    @Autowired
    @Qualifier("invoiceJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Override
    public void sendProjectInfo(final ClientDTO clientDTO) {
        LOGGER.info("Sending Project Info {} to external Invoice system", clientDTO);

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(clientDTO);
                objectMessage.setJMSType("Invoice-XML-Format");
                objectMessage.setJMSMessageID("345-7676-" + clientDTO.getClientId());
                objectMessage.setJMSPriority(7);
                return objectMessage;
            }
        });
    }
}
