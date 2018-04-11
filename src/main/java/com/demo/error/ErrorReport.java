package com.demo.error;

import java.io.IOException;

import com.google.cloud.logging.Severity;

public class ErrorReport {
	
	private static LoggerUtil logger= LoggerUtil.getInstance();

	private static ReportUtil reporter= ReportUtil.getInstance();
	
	
	public static void main(String[] args) throws Exception {
		
		String appName="ErrorLogUtils";
		
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		
		reporter.report(ErrorReport.class.getName(), Thread.currentThread().getStackTrace()[1].
			      getLineNumber(), methodName, "Report Error Message", appName);
		
		logger.log(Severity.ERROR, appName, "ERROR part");
		
		logger.log(Severity.CRITICAL, appName, "CRITICAL part");
		
		logger.log(Severity.WARNING, appName, "WARNING part");
		
		logger.log(Severity.ALERT, appName, "ALERT part");
	}

}
