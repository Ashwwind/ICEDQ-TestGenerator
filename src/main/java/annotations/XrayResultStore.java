package annotations;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XrayResultStore {

	private static Map<String, String> results = new ConcurrentHashMap<>();

	public static void addResult(String testKey, String status) {
		results.merge(testKey, status, (oldStatus, newStatus) -> {
			if ("FAILED".equals(oldStatus) || "FAILED".equals(newStatus))
				return "FAILED";
			if ("SKIPPED".equals(oldStatus))
				return "SKIPPED";
			return newStatus;
		});
	}

	public static Map<String, String> getResults() {
		return results;
	}

	public static void clearResults() {
		results.clear();
	}
}
