package com.rechnungswesen.app.xmlprocessor;

import org.springframework.stereotype.Component;

@Component
public class JsonToXmlAdaptor implements XmlProcessor {
	private final JsonData jsonData;

	public JsonToXmlAdaptor(JsonData jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public String getXmlData() {
		return this.jsonData.getData();
	}
}
