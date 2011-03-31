@Grab(group='org.apache.activemq', module='activemq-all', version='5.4.2')

import javax.jms.Connection
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage
import javax.jms.ObjectMessage

import org.apache.activemq.ActiveMQConnection
import org.apache.activemq.ActiveMQConnectionFactory

ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
Connection connection = connectionFactory.createConnection()
connection.start()

Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
Destination dest = session.createQueue('HSQ')

MessageProducer prod = session.createProducer(dest)

ObjectMessage msg = session.createObjectMessage()
String applicantId = '2011201712'

def applicantList = []
applicantList << '2011201714' << '2011201718' << '2011274547'

msg.setObject(applicantList)
//msg.setObject(applicantId)
//msg.setJMSMessageID('legacy')

println "sending msg ${msg}"
prod.send(msg)
	

println "Done!"

System.exit(0)