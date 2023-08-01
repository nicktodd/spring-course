package com.conygre.webservices;

import org.jdom.Element;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CompactDiscEndpoint {

	private final String namespace = "www.conygre.com/compactdiscs";
	
	
	@PayloadRoot(localPart = "getCompactDiscRequest", 
			namespace = "www.conygre.com/compactdiscs")
	@ResponsePayload
	public Element getCompactDisc(@RequestPayload Element request) throws Exception{
		Element e = new Element("getCompactDiscResponse", 
				"con", namespace);
		e.addContent(new Element("id", "con", namespace).setText("2"));
		e.addContent(new Element("title", "con", namespace).setText("Never Say Never"));
		e.addContent(new Element("artist", "con", namespace).setText("Justin Bieber"));
		e.addContent(new Element("price", "con", namespace).setText("12.99"));
		e.addContent(new Element("tracks", "con", namespace).setText("7"));
		return e;
	}	
}