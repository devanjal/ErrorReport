package com.demo.error;

import com.google.api.Service;
import com.google.api.services.compute.*;
import com.google.api.services.compute.Compute.Instances;
import com.google.api.services.compute.model.ServiceAccount;
import com.google.auth.oauth2.CloudShellCredentials;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;
import com.google.devtools.clouderrorreporting.v1beta1.ProjectName;
import com.google.devtools.clouderrorreporting.v1beta1.ServiceContext;
import com.google.cloud.MetadataConfig;

import java.util.Collections;
public class LoggerUtil {
	
	 private static LoggerUtil instance = null;
	 
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
		   System.out.println("**************"+MetadataConfig.getInstanceId());
		    
		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(message))
		        .setSeverity(level)
		        .setLogName(appName)
		        .setResource(MonitoredResource.newBuilder("gce_instance").build())
		        .build();
		    logging.write(Collections.singleton(entry));
		  }
}
