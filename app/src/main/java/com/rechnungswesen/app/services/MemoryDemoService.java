package com.rechnungswesen.app.services;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryDemoService {

	private final List<DemoObject> objects = new ArrayList<>();

	public void fillMemory(int count) {
		for (int i = 0; i < count; i++) {
			objects.add(new DemoObject(i));
		}
	}

	public void clearMemory() {
		objects.clear();
	}

	public int getObjectCount() {
		return objects.size();
	}

	static class DemoObject {
		private final int id;
		private final byte[] data = new byte[1024];

		DemoObject(int id) {
			this.id = id;
		}
	}
}

