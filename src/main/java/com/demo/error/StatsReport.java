package com.demo.error;

import com.google.api.Metric;
import com.google.api.MetricDescriptor.MetricKind;
import com.google.api.MonitoredResource;
import com.google.cloud.ServiceOptions;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.monitoring.v3.TypedValue;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import com.google.monitoring.v3.CreateTimeSeriesRequest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StatsReport {
	
	 private static StatsReport instance = null;
	 
	   protected StatsReport() {
		   
	   }
	   
	   public static StatsReport getInstance() {
	      if(instance == null) {
	         instance = new StatsReport();
	      }
	      return instance;
	   }

	@SuppressWarnings("deprecation")
	public void writeStatsData(String metricUrl, String projectId, long data, String categoryKey, String categoryValue) throws Exception {
		
		// String projectId = ServiceOptions.getDefaultProjectId();
		 MetricServiceClient metricServiceClient = MetricServiceClient.create();
		 TimeInterval interval = TimeInterval.newBuilder()
			        .setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
			//	 .setStartTime(Timestamps.parse("2018-04-12T08:01:23.045123456Z"))
			//	 .setEndTime(Timestamps.parse("2018-04-12T09:01:23.045123456Z"))
			        .build();
			    TypedValue value = TypedValue.newBuilder()
			        .setDoubleValue(data)
			        .build();
			    Point point = Point.newBuilder()
			        .setInterval(interval)
			        .setValue(value)
			        .build();
			    List<Point> pointList = new ArrayList<Point>();
			    pointList.add(point);

			    ProjectName name = ProjectName.of(projectId);
			    
			    Map<String, String> metricLabels = new HashMap<String, String>();
			    metricLabels.put(categoryKey, categoryValue);
			    Metric metric = Metric.newBuilder()
				        .setType("agent.googleapis.com/agent/api_request_count")
				        .putAllLabels(metricLabels)
				        .build();

			    Map<String, String> resourceLabels = new HashMap<String, String>();
			    resourceLabels.put("project_id", projectId);
			    MonitoredResource resource = MonitoredResource.newBuilder()
			        .setType("global")
			        .putAllLabels(resourceLabels)
			        .build();

			    TimeSeries timeSeries = TimeSeries.newBuilder()
			        .setMetric(metric)
			        .setResource(resource)
			        .addAllPoints(pointList)
			        .build();
			    List<TimeSeries> timeSeriesList = new ArrayList<TimeSeries>();
			    timeSeriesList.add(timeSeries);

			    CreateTimeSeriesRequest request = CreateTimeSeriesRequest.newBuilder()
			        .setNameWithProjectName(name)
			        .addAllTimeSeries(timeSeriesList)
			        .build();

			    metricServiceClient.createTimeSeries(request);

			    System.out.printf("Done writing time series data.%n");

			    metricServiceClient.close();
	}


}
