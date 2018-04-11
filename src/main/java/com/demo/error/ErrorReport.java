package com.demo.error;


import com.google.cloud.logging.Severity;

public class ErrorReport {
	
	private static LoggerUtil logger= LoggerUtil.getInstance();

	private static ReportUtil reporter= ReportUtil.getInstance();
	
	private static StatsReport writer= StatsReport.getInstance();
	
	public static void main(String[] args) throws Exception {
		
		String appName="ErrorLogUtils";
		
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		
		
		writer.writeStatsData("devanjal", "wise-diagram-197921", 123456789, "statsCategory", "MID");
		
		
		
		
		
		reporter.report(ErrorReport.class.getName(), 27, methodName, "Report Error Message in wise-diagram-197921", appName);
		
		
		
		
		
		logger.log(Severity.ERROR, appName, "ERROR part");
		
		logger.log(Severity.CRITICAL, appName, "CRITICAL part");
		
		logger.log(Severity.WARNING, appName, "WARNING part");
		
		logger.log(Severity.ALERT, appName, "ALERT part");
	}

}
