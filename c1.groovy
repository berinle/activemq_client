@Grab(group='org.apache.activemq', module='activemq-all', version='5.4.2')

import javax.jms.*

import org.apache.activemq.ActiveMQConnection
import org.apache.activemq.ActiveMQConnectionFactory

ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
Connection connection = connectionFactory.createConnection()
connection.start()

Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
//Destination dest = session.createQueue('queue.hibernatesearch')
Destination dest = session.createQueue('HibernateSearchController')

MessageConsumer consumer = session.createConsumer(dest)

Message msg 
while(true){
	msg = consumer.receive()
	def c = Calendar.instance
	def now = c.getTime()
	println  "${now} got message ${msg}"
	//println "${msg.inspect()}"
	//println "${msg.getClass()}"
	Thread.sleep(5000)
}

println "Quiting...bye"