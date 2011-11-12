import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author berinle
 */
public class Producer {
    
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String QUEUE = "ERAS.Lucene.Queue";

    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination dest = session.createQueue(QUEUE);

            MessageProducer prod = session.createProducer(dest);

            ObjectMessage msg = session.createObjectMessage();

            List<String> applicantList = new ArrayList<String>(){{
                add("1234");
                add("4567");
                add("5678");
            }};

            for (String s : applicantList) {
                msg.setObject(s);
                System.out.printf("sending msg %s%n", msg);
                prod.send(msg);
            }

            System.out.println("Done!");

            System.exit(0);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
