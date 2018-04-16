package com.demo.error;


import com.google.api.client.util.Value;
import com.google.cloud.MetadataConfig;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.rpc.ResourceInfo;
import com.google.cloud.MonitoredResourceDescriptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
			
		//	System.out.println(logSwitch);
			
			String instanceId=MetadataConfig.getInstanceId();
			String instanceZone=MetadataConfig.getZone();
			String projectId=MetadataConfig.getProjectId();
			
			HashMap<String, String> label = new HashMap<String, String>();
			label.put("instance_id", instanceId);
			label.put("project_id", projectId);
			label.put("zone", instanceZone);
		    Logging logging = LoggingOptions.getDefaultInstance().getService();
		    
		    List<FieldDescriptor> list =ResourceInfo.getDescriptor().getFields();
		  
		    
		    	System.out.println(list.contains("gce_instance"));
		    
		    
		 //  System.out.println("**************"+ResourceInfo.getDescriptor().getFields().isEmpty());
		   
		    
		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(message))
		        .setSeverity(level)
		        .setLogName(appName)
		        .setResource(MonitoredResource.newBuilder("gce_instance").setLabels(label).build())
		        .build();
		    logging.write(Collections.singleton(entry));
		  }
}
