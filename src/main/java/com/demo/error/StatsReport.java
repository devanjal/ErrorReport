package com.demo.error;

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.cloud.ServiceOptions;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.monitoring.v3.TypedValue;
import com.google.protobuf.util.Timestamps;
import com.google.monitoring.v3.CreateTimeSeriesRequest;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StatsReport {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		
		
		// TODO Auto-generated method stub
		 String projectId = ServiceOptions.getDefaultProjectId();
		 MetricServiceClient metricServiceClient = MetricServiceClient.create();
		 TimeInterval interval = TimeInterval.newBuilder()
			        .setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
			        .build();
			    TypedValue value = TypedValue.newBuilder()
			        .setDoubleValue(1923.45)
			        .build();
			    Point point = Point.newBuilder()
			        .setInterval(interval)
			        .setValue(value)
			        .build();
			    List<Point> pointList = new ArrayList<Point>();
			    pointList.add(point);

			    ProjectName name = ProjectName.of(projectId);

			    // Prepares the metric descriptor
			    Map<String, String> metricLabels = new HashMap<String, String>();
//			    metricLabels.put("Site_Count", "MID");
//			    metricLabels.put("Other_Count", "Other");
			    Metric metric = Metric.newBuilder()
			        .setType("custom.googleapis.com/custom/devanjal")
			        .putAllLabels(metricLabels)
			        .build();

			    // Prepares the monitored resource descriptor
			    Map<String, String> resourceLabels = new HashMap<String, String>();
			    resourceLabels.put("project_id", projectId);
			    MonitoredResource resource = MonitoredResource.newBuilder()
//			        .setType("global")
//			        .putAllLabels(resourceLabels)
			        .build();

			    // Prepares the time series request
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

			    // Writes time series data
			    metricServiceClient.createTimeSeries(request);

			    System.out.printf("Done writing time series data.%n");

			    metricServiceClient.close();
	}


}
