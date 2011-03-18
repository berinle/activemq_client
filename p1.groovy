@Grab(group='org.apache.activemq', module='activemq-all', version='5.4.2')

import javax.jms.Connection
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

import org.apache.activemq.ActiveMQConnection
import org.apache.activemq.ActiveMQConnectionFactory

ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
Connection connection = connectionFactory.createConnection()
connection.start()

Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
Destination dest = session.createQueue('queue.hibernatesearch')

MessageProducer prod = session.createProducer(dest)

(1..100).each{
	TextMessage msg = session.createTextMessage('This is a test ' + new Date())
	println "sending msg ${it}"
	prod.send(msg)
	
	Thread.sleep(1000)
}

println "Done!"