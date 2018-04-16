package com.demo.error;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;
import com.google.common.io.Resources;
import com.google.devtools.clouderrorreporting.v1beta1.ServiceContext;
import com.google.monitoring.v3.GroupNameType;
import com.google.protobuf.Type;
import com.google.rpc.ResourceInfo;

import io.grpc.Grpc;
import io.grpc.Server;

import com.google.api.Service;
import com.google.api.services.compute.model.Project;
import com.google.cloud.MetadataConfig;
import com.google.cloud.ServiceOptions;

import java.util.Collections;
import java.util.HashMap;
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
			
			String instanceId=MetadataConfig.getInstanceId();
			String instanceZone=MetadataConfig.getZone();
			String projectId=MetadataConfig.getProjectId();
			
			HashMap<String, String> label = new HashMap<String, String>();
			label.put("instance_id", instanceId);
			label.put("project_id", projectId);
			label.put("zone", instanceZone);
			
			
			
			//   MonitoredResource resource = MonitoredResource.fromPb(com.google.api.MonitoredResource.getDefaultInstance().getDefaultInstanceForType());
		
		    Logging logging = LoggingOptions.getDefaultInstance().getService();
		   System.out.println("**************"+ResourceInfo.getDescriptor());
		    
		  //  MonitoredResourceDescriptor.LabelDescriptor.ValueType.STRING.toString();
		    
		    LogEntry entry = LogEntry.newBuilder(StringPayload.of(message))
		        .setSeverity(level)
		        .setLogName(appName)
		        .setResource(MonitoredResource.newBuilder("gce_instance").setLabels(label).build())
		        .build();
		    logging.write(Collections.singleton(entry));
		  }
}
