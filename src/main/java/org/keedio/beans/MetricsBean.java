package org.keedio.beans;

public class MetricsBean {

    private String metricName;
    private String metricUnit;
    private String metricTimestamp;
    private float metricValue;

    public MetricsBean() {
        metricName = "";
        metricUnit = "";
        metricTimestamp = "";
        metricValue = 0;
    }

    public MetricsBean(String metricName, String metricUnit, String metricTimestamp, float metricValue) {
        this.metricName = metricName;
        this.metricUnit = metricUnit;
        this.metricTimestamp = metricTimestamp;
        this.metricValue = metricValue;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public String getMetricTimestamp() {
        return metricTimestamp;
    }

    public void setMetricTimestamp(String metricTimestamp) {
        this.metricTimestamp = metricTimestamp;
    }

    public float getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(float metricValue) {
        this.metricValue = metricValue;
    }
}
