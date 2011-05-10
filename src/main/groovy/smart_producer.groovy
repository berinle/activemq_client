//see @ http://groovy.329449.n5.nabble.com/How-to-easily-read-from-System-in-td367942.html


import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.*

ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616")
Connection connection = connectionFactory.createConnection()
connection.start()

Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
Destination dest = session.createQueue('ERAS.Lucene.Queue')

MessageProducer prod = session.createProducer(dest)

TextMessage msg = session.createTextMessage()

/*msg.setText('2011201714')
prod.send(msg)
println 'done'*/

System.in.withReader {

    while (true) {
        print 'Which applicant do you wish to sync: '
        def applicantId = it.readLine()
        if (applicantId?.trim()?.length() != 0) {
            msg.setText(applicantId)

            println "Sending message ${msg}"
            prod.send msg

            println "Done!"
        }
    }
}


System.exit(0)
