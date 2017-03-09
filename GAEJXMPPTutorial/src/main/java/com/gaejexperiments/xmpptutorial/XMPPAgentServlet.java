package com.gaejexperiments.xmpptutorial;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 

/**
 * Servlet implementation class XMPPAgentServlet
 */
@SuppressWarnings("serial") 

public class XMPPAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final Logger _log = Logger.getLogger(XMPPAgentServlet.class.getName()); 
	
    public XMPPAgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		XMPPService xmpp = XMPPServiceFactory.getXMPPService(); 
//		Message msg = xmpp.parseMessage(request); 
//		
//		//JID fromJid = msg.getFromJid(); 
//		JID fromJid = new JID("bluegasus@gmail.com"); 
//		String body = msg.getBody(); 
//		
//		Message replyMessage = new MessageBuilder().withRecipientJids(fromJid) .withBody("TEXT_TO_SEND_TO_RECIPIENT") .build();
//		
//		//Compose the Message Object i.e. replyMessage 
//		if (xmpp.getPresence(fromJid).isAvailable()) { 
//		SendResponse status = xmpp.sendMessage(replyMessage); 
//		//Take appropriate action based on status SUCCESS or FAIL i.e. log an error, update database, etc 
//		} 
		
		try { 
			String strStatus = ""; 
			XMPPService xmpp = XMPPServiceFactory.getXMPPService(); 
			//STEP 2 
			Message msg = xmpp.parseMessage(request); 
			JID fromJid = msg.getFromJid(); 
			String body = msg.getBody(); 
			_log.info("Received a message from " + fromJid + " and body = " + 
			body); 
			//STEP 3 
			String msgBody = "You sent me : " + body; 
			Message replyMessage = new MessageBuilder() 
			.withRecipientJids(fromJid) 
			.withBody(msgBody) 
			.build(); 
			//STEP 4 
			boolean messageSent = false; 
			if (xmpp.getPresence(fromJid).isAvailable()) { 
			SendResponse status = xmpp.sendMessage(replyMessage); 
			messageSent = (status.getStatusMap().get(fromJid) == 
			SendResponse.Status.SUCCESS); 
			} 
			//STEP 5 
			if (messageSent) { 
			strStatus = "Message has been sent successfully"; 
			} 
			else { 
			strStatus = "Message could not be sent"; 
			} 
			_log.info(strStatus); 
			} catch (Exception e) { 
			_log.log(Level.SEVERE,e.getMessage()); 
			
			} 
		
	}

}
