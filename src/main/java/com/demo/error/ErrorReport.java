package com.demo.error;

import java.io.IOException;

public class ErrorReport {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello");
		
		ReportUtil report = new ReportUtil();
		String className="ErrorReport.java";
		String methodName="main()";
		String errorMessage="Test Code";
		int line = 13;
		report.report(className, line, methodName, errorMessage);
	}

}
