package com.demo.error;

import java.io.IOException;

public class ErrorReport {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello");
		
		ReportUtil report = new ReportUtil();
		String className=ErrorReport.class.getName();
		String methodName=ErrorReport.class.getEnclosingMethod().getName();
		String errorMessage="Test Code";
	//	int line = 13;
		report.report(className,Thread.currentThread().getStackTrace()[1].
			      getLineNumber(), methodName, errorMessage);
	}

}
