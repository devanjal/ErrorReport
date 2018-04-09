package com.demo.error;

import java.io.IOException;

public class ErrorReport {

	public static void main(String[] args) throws IOException {
		
		ReportUtil report = new ReportUtil();
		String className=ErrorReport.class.getName();
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		String errorMessage="Test Code";
		
		report.report(className,Thread.currentThread().getStackTrace()[1].
			      getLineNumber(), methodName, errorMessage);
	}

}
