package com.demo.error;

import java.io.IOException;

import com.google.cloud.logging.Severity;

public class ErrorReport {
	
	private static LoggerUtil logger= LoggerUtil.getInstance();

	public static void main(String[] args) throws Exception {
		
		ReportUtil report = new ReportUtil();
		
	//	LoggerUtil logger= LoggerUtil.getInstance();
		
		String appName="MIDlogDemo";
		
//		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
//		String errorMessage="Test Code";
		
/*		report.report(className,Thread.currentThread().getStackTrace()[1].
			      getLineNumber(), methodName, errorMessage);*/
		
		logger.log(Severity.ERROR, appName, "ERROR part");
		
		logger.log(Severity.CRITICAL, appName, "CRITICAL part");
		
		logger.log(Severity.WARNING, appName, "WARNING part");
		
		logger.log(Severity.ALERT, appName, "ALERT part");
	}

}
