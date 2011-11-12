import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author berinle
 */
public class Consumer {
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String QUEUE = "ERAS.Lucene.Queue";

    public static void main(String[] args) {

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination dest = session.createQueue(QUEUE);

            MessageConsumer consumer = session.createConsumer(dest);

            Message msg;
            System.out.println("thread going to sleep...");
            while(true){
                msg = consumer.receive();
                Calendar c = Calendar.getInstance();
                Date now = c.getTime();
                System.out.printf("%s got message %s%n%n", now, msg);
                Thread.sleep(2000);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
