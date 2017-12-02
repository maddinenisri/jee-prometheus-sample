package com.mdstech.sample;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationGaugeCollector extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<>();
        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("track_inprogress_requests", "Current Inprogress API Requests", Arrays.asList("labelname"));
        labeledGauge.addMetric(Arrays.asList("env"), 4);
        labeledGauge.addMetric(Arrays.asList("appname"), 5);
        mfs.add(labeledGauge);
        return mfs;
    }
}
