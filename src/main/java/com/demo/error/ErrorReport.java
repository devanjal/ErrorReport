package com.demo.error;

import java.io.IOException;

import com.google.cloud.logging.Severity;

public class ErrorReport {

	public static void main(String[] args) throws Exception {
		
		ReportUtil report = new ReportUtil();
		
		LoggerUtil logger= LoggerUtil.getInstance();
		
		String className=ErrorReport.class.getName();
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		String errorMessage="Test Code";
		
/*		report.report(className,Thread.currentThread().getStackTrace()[1].
			      getLineNumber(), methodName, errorMessage);*/
		
		logger.log(Severity.ERROR, className, "ERROR part");
		logger.log(Severity.CRITICAL, className, "CRITICAL part");
		logger.log(Severity.WARNING, className, "WARNING part");
		logger.log(Severity.ALERT, className, "ALERT part");
	}

}
