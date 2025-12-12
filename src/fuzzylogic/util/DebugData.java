package fuzzylogic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DebugData {

	private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

	public void add(String msg) {
		if (msg == null) return;
		messages.add(msg);
	}

	public void clear() {
		messages.clear();
	}

	public List<String> getMessages() {
		synchronized (messages) {
			return new ArrayList<>(messages);
		}
	}

	public boolean isEmpty() {
		return messages.isEmpty();
	}
}
