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

	public void report(String className, int line, String methodName, String errorMessage) throws IOException {
		
		ServiceContext service;
		service.getResourceType();
		
		System.out.println(ServiceContext.getDefaultInstance().getResourceType() + " AND  ******" + ServiceContext.getDefaultInstance().getService());
		ReportErrorsServiceClient reportErrorsServiceClient = ReportErrorsServiceClient.create();
		ErrorContext errorContext = ErrorContext.newBuilder()
		          .setReportLocation(SourceLocation.newBuilder()
		              .setFilePath(className)
		              .setLineNumber(line)
		              .setFunctionName(methodName)
		              .build())
		          .build();
		
		      ReportedErrorEvent customErrorEvent = ReportedErrorEvent.getDefaultInstance()
		          .toBuilder().setServiceContext(ServiceContext.getDefaultInstance())
		          .setMessage(errorMessage)
		          .setContext(errorContext)
		          .build();

		      ProjectName projectName = ProjectName.of(ServiceOptions.getDefaultProjectId());
		      reportErrorsServiceClient.reportErrorEvent(projectName, customErrorEvent);
	}
}
