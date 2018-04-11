package com.demo.error;

import java.io.IOException;

import com.google.cloud.ServiceOptions;
import com.google.cloud.errorreporting.v1beta1.ReportErrorsServiceClient;
import com.google.devtools.clouderrorreporting.v1beta1.ErrorContext;
import com.google.devtools.clouderrorreporting.v1beta1.ProjectName;
import com.google.devtools.clouderrorreporting.v1beta1.ReportedErrorEvent;
import com.google.devtools.clouderrorreporting.v1beta1.ServiceContext;
import com.google.devtools.clouderrorreporting.v1beta1.SourceLocation;

public class ReportUtil {

	
	 private static ReportUtil instance = null;
	 
	   protected ReportUtil() {
		   
	   }
	   
	   public static ReportUtil getInstance() {
	      if(instance == null) {
	         instance = new ReportUtil();
	      }
	      return instance;
	   }
	public void report(String className, int line, String methodName, String errorMessage, String appName) throws IOException {
		
		
		
		ReportErrorsServiceClient reportErrorsServiceClient = ReportErrorsServiceClient.create();
		ErrorContext errorContext = ErrorContext.newBuilder()
		          .setReportLocation(SourceLocation.newBuilder()
		              .setFilePath(className)
		              .setLineNumber(line)
		              .setFunctionName(methodName)
		              .build())
		          .build();
		
		
		
		ProjectName projectName = ProjectName.of(ServiceOptions.getDefaultProjectId());
		      ReportedErrorEvent customErrorEvent = ReportedErrorEvent.getDefaultInstance()
		          .toBuilder()
		          .setServiceContext(ServiceContext.newBuilder()
		        		  .setService(appName)
		        		  .setResourceType("gce_instance")
		        		  .build())
		          .setMessage(errorMessage)
		          .setContext(errorContext)
		          .build();

		      
	  reportErrorsServiceClient.reportErrorEvent(projectName, customErrorEvent);
	}
}
