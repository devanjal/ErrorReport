package com.demo.error;

import java.io.IOException;

import com.google.cloud.logging.Severity;

public class ErrorReport {
	
	private static LoggerUtil logger= LoggerUtil.getInstance();

	private static ReportUtil reporter= ReportUtil.getInstance();
	
	private static StatsReport writer= StatsReport.getInstance();
	
	public static void main(String[] args) throws Exception {
		
		String appName="ErrorLogUtils";
		
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();	
		
		writer.writeStatsData("devanjal", "wise-diagram-197921", 11111111, "site_count", "MID");
		
		try {
		throw new RuntimeException();
		}
		catch (RuntimeException e) {
			reporter.report(ErrorReport.class.getName(), Thread.currentThread().getStackTrace()[1].
				      getLineNumber(), methodName, e.fillInStackTrace().toString() , appName);
		}
		logger.log(Severity.ERROR, appName, "ERROR part");
		
		logger.log(Severity.CRITICAL, appName, "CRITICAL part");
		
		logger.log(Severity.WARNING, appName, "WARNING part");
		
		logger.log(Severity.ALERT, appName, "ALERT part");
	}

}
