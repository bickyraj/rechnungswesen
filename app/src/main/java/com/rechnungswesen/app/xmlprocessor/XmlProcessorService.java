package com.rechnungswesen.app.xmlprocessor;

import org.springframework.stereotype.Service;

@Service
public class XmlProcessorService {

	public String download(XmlProcessor xmlProcessor) {
		return xmlProcessor.getXmlData();
	}
}
