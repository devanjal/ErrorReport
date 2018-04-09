package com.demo.error;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;




import java.util.Collections;
public class LoggerUtil {
	 public static void main(String... args) throws Exception {

		    // Instantiates a client
		    Logging logging = LoggingOptions.getDefaultInstance().getService();

		    // The name of the log to write to
		    String logName = args[0];  // "my-log";

		    // The data to write to the log
		    String text = "Hello, world!";

		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(text))
		        .setSeverity(Severity.ERROR)
		        .setLogName(logName)
		        .setResource(MonitoredResource.newBuilder("global").build())
		        .build();

		    // Writes the log entry asynchronously
		    logging.write(Collections.singleton(entry));

		    System.out.printf("Logged: %s%n", text);
		  }
}
