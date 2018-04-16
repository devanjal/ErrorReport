package com.demo.error;

import com.google.api.services.compute.model.Instance;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;


import java.util.Collections;
public class LoggerUtil {
	
	 private static LoggerUtil instance = null;
	 private static Instance instanceId = new Instance();
	   protected LoggerUtil() {
		   
	   }
	   public static LoggerUtil getInstance() {
	      if(instance == null) {
	         instance = new LoggerUtil();
	      }
	      return instance;
	   }

		public void log(Severity level, String appName, String message) throws Exception {
			
		    Logging logging = LoggingOptions.getDefaultInstance().getService();
		   System.out.println("**************"+instanceId.getId());
		    
		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(message))
		        .setSeverity(level)
		        .setLogName(appName)
		        .setResource(MonitoredResource.newBuilder("gce_instance").build())
		        .build();
		    logging.write(Collections.singleton(entry));
		  }
}
