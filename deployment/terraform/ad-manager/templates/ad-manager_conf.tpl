ad-manager {
  streams {
    application.id = "ad-manager-2"
    bootstrap.servers = "${kafka_endpoint}"
    default.value.serde = "com.expedia.adaptivealerting.kafka.serde.JsonPojoSerde"
    JsonPojoClass = "com.expedia.adaptivealerting.core.data.MappedMetricData"
    default.timestamp.extractor = "com.expedia.adaptivealerting.kafka.serde.MappedMetricDataTimestampExtractor"
  }
  detectors {
    aquila-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.aquila.AquilaAnomalyDetectorFactory"
      config {
        uri = "${aquila_uri}"
      }
    }
    constant-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.BasicAnomalyDetectorFactory"
      config {
        detectorClass = "com.expedia.adaptivealerting.anomdetect.constant.ConstantThresholdAnomalyDetector"
      }
    }
    cusum-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.BasicAnomalyDetectorFactory"
      config {
        detectorClass = "com.expedia.adaptivealerting.anomdetect.cusum.CusumAnomalyDetector"
      }
    }
    ewma-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.BasicAnomalyDetectorFactory"
      config {
        detectorClass = "com.expedia.adaptivealerting.anomdetect.ewma.EwmaAnomalyDetector"
      }
    }
    pewma-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.BasicAnomalyDetectorFactory"
      config {
        detectorClass = "com.expedia.adaptivealerting.anomdetect.pewma.PewmaAnomalyDetector"
      }
    }
    rcf-detector {
      factory = "com.expedia.adaptivealerting.anomdetect.rcf.RandomCutForestAnomalyDetectorFactory"
      config {
      }
    }
  }
  health.status.path = "/app/isHealthy"
  inbound-topic = "mapped-metrics-2"
  outbound-topic = "anomalies-2"
  model-service-uri-template = "${modelservice_uri_template}"
}
