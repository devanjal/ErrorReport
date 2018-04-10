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
			
		    Logging logging = LoggingOptions.getDefaultInstance().getService();

		    String logName = "test_logs_danal";

		    String text = "Danal StackDriver Testing";

		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(text))
		        .setSeverity(Severity.ERROR)
		        .setLogName(logName)
		        .setResource(MonitoredResource.newBuilder("global").build())
		        .build();

		    logging.write(Collections.singleton(entry));

		    System.out.printf("Logged: %s%n", text);
		  }
}
