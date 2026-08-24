package com.rechnungswesen.app.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class IdempotencyKey {
	private final String value;
}
