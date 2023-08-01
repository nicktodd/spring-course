import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;


public class TestSAAJ {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SOAPConnectionFactory cFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection conn = cFactory.createConnection();
		MessageFactory mFactory = MessageFactory.newInstance();
		SOAPMessage message  = mFactory.createMessage();
		SOAPBody body = message.getSOAPBody();
		QName qName = 
			new QName ("http://footballpool.dataaccess.eu","YellowCardsTotal","m");
		body.addBodyElement(qName);
		SOAPMessage resultMessage = conn.call(message, "http://footballpool.dataaccess.eu/data/info.wso");
		resultMessage.writeTo(System.out);

	}

}
