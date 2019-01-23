locals {
  kafka_endpoint =  "${var.kafka_hostname}:${var.kafka_port}"
}

# ========================================
# Adaptive Alerting
# ========================================

module "modelservice" {
  source = "modelservice"

  # Docker
  image = "expediadotcom/adaptive-alerting-modelservice:${var.alerting["version"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.modelservice["enabled"]}"
  replicas = "${var.modelservice["instances"]}"
  cpu_limit = "${var.modelservice["cpu_limit"]}"
  cpu_request = "${var.modelservice["cpu_request"]}"
  memory_limit = "${var.modelservice["memory_limit"]}"
  memory_request = "${var.modelservice["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"
  aa_ui_cname = "${var.aa_ui_cname}"

  # Environment
  jvm_memory_limit = "${var.modelservice["jvm_memory_limit"]}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  graphite_enabled = "${var.graphite_enabled}"
  env_vars = "${var.modelservice["environment_overrides"]}"

  # App
  db_endpoint = "${var.modelservice["db_endpoint"]}"
}

module "ad-mapper" {
  source = "ad-mapper"

  # Docker
  image = "${var.ad-mapper["image"]}"
  image_pull_policy = "${var.ad-mapper["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.ad-mapper["enabled"]}"
  replicas = "${var.ad-mapper["instances"]}"
  cpu_limit = "${var.ad-mapper["cpu_limit"]}"
  cpu_request = "${var.ad-mapper["cpu_request"]}"
  memory_limit = "${var.ad-mapper["memory_limit"]}"
  memory_request = "${var.ad-mapper["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  jvm_memory_limit = "${var.ad-mapper["jvm_memory_limit"]}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  graphite_enabled = "${var.graphite_enabled}"
  env_vars = "${var.ad-mapper["environment_overrides"]}"

  # App
  kafka_endpoint = "${local.kafka_endpoint}"
  modelservice_uri_template = "${var.ad-mapper["modelservice_uri_template"]}"
}

module "ad-manager" {
  source = "ad-manager"

  # Docker
  image = "${var.ad-manager["image"]}"
  image_pull_policy = "${var.ad-manager["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.ad-manager["enabled"]}"
  replicas = "${var.ad-manager["instances"]}"
  cpu_limit = "${var.ad-manager["cpu_limit"]}"
  cpu_request = "${var.ad-manager["cpu_request"]}"
  memory_limit = "${var.ad-manager["memory_limit"]}"
  memory_request = "${var.ad-manager["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  jvm_memory_limit = "${var.ad-manager["jvm_memory_limit"]}"
  graphite_enabled = "${var.graphite_enabled}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  env_vars = "${var.ad-manager["environment_overrides"]}"

  # App
  kafka_endpoint = "${local.kafka_endpoint}"
  aquila_uri = "${var.ad-manager["aquila_uri"]}"
  models_region = "${var.ad-manager["models_region"]}"
  models_bucket = "${var.ad-manager["models_bucket"]}"
  modelservice_uri_template = "${var.ad-manager["modelservice_uri_template"]}"
}

/*
module "mc-a2m-mapper" {
  source = "mc-a2m-mapper"

  # Docker
  image = "${var.mc-a2m-mapper["image"]}"
  image_pull_policy = "${var.mc-a2m-mapper["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.mc-a2m-mapper["enabled"]}"
  replicas = "${var.mc-a2m-mapper["instances"]}"
  cpu_limit = "${var.mc-a2m-mapper["cpu_limit"]}"
  cpu_request = "${var.mc-a2m-mapper["cpu_request"]}"
  memory_limit = "${var.mc-a2m-mapper["memory_limit"]}"
  memory_request = "${var.mc-a2m-mapper["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  graphite_enabled = "${var.graphite_enabled}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  jvm_memory_limit = "${var.mc-a2m-mapper["jvm_memory_limit"]}"
  env_vars = "${var.mc-a2m-mapper["environment_overrides"]}"

  # App
  kafka_input_endpoint = "${var.mc-a2m-mapper["kakfa_input_endpoint"]}"
  kafka_input_topic = "${var.mc-a2m-mapper["kafka_input_topic"]}"
  kafka_input_serde_key = "${var.mc-a2m-mapper["kafka_input_serde_key"]}"
  kafka_input_serde_value = "${var.mc-a2m-mapper["kafka_input_serde_value"]}"
  kafka_input_extractor_timestamp = "${var.mc-a2m-mapper["kafka_input_extractor_timestamp"]}"
  kafka_output_endpoint = "${var.mc-a2m-mapper["kafka_output_endpoint"]}"
  kafka_output_topic = "${var.mc-a2m-mapper["kafka_output_topic"]}"
  kafka_output_serde_key = "${var.mc-a2m-mapper["kafka_output_serde_key"]}"
  kafka_output_serde_value = "${var.mc-a2m-mapper["kafka_output_serde_value"]}"
}
*/

module "notifier" {
  source = "notifier"

  # Docker
  image = "expediadotcom/adaptive-alerting-notifier:${var.alerting["version"]}"
  image_pull_policy = "${var.notifier["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.notifier["enabled"]}"
  replicas = "${var.notifier["instances"]}"
  cpu_limit = "${var.notifier["cpu_limit"]}"
  cpu_request = "${var.notifier["cpu_request"]}"
  memory_limit = "${var.notifier["memory_limit"]}"
  memory_request = "${var.notifier["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  jvm_memory_limit = "${var.notifier["jvm_memory_limit"]}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  graphite_enabled = "${var.graphite_enabled}"
  env_vars = "${var.notifier["environment_overrides"]}"

  # App
  kafka_endpoint = "${local.kafka_endpoint}"
  webhook_url = "${var.notifier["webhook_url"]}"
}

# ========================================
# Aquila
# ========================================

module "aquila-detector" {
  source = "aquila-detect"

  # Docker
  image = "${var.aquila-detector["image"]}"
  image_pull_policy = "${var.aquila-detector["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.aquila-detector["enabled"]}"
  replicas = "${var.aquila-detector["instances"]}"
  cpu_limit = "${var.aquila-detector["cpu_limit"]}"
  cpu_request = "${var.aquila-detector["cpu_request"]}"
  memory_limit = "${var.aquila-detector["memory_limit"]}"
  memory_request = "${var.aquila-detector["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  jvm_memory_limit = "${var.aquila-detector["jvm_memory_limit"]}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  graphite_enabled = "${var.graphite_enabled}"
  env_vars = "${var.aquila-detector["environment_overrides"]}"
}

module "aquila-trainer" {
  source = "aquila-train"

  # Docker
  image = "${var.aquila-trainer["image"]}"
  image_pull_policy = "${var.aquila-trainer["image_pull_policy"]}"

  # Kubernetes
  namespace = "${var.app_namespace}"
  enabled = "${var.aquila-trainer["enabled"]}"
  replicas = "${var.aquila-trainer["instances"]}"
  cpu_limit = "${var.aquila-trainer["cpu_limit"]}"
  cpu_request = "${var.aquila-trainer["cpu_request"]}"
  memory_limit = "${var.aquila-trainer["memory_limit"]}"
  memory_request = "${var.aquila-trainer["memory_request"]}"
  node_selector_label = "${var.node_selector_label}"
  kubectl_executable_name = "${var.kubectl_executable_name}"
  kubectl_context_name = "${var.kubectl_context_name}"

  # Environment
  jvm_memory_limit = "${var.aquila-trainer["jvm_memory_limit"]}"
  graphite_hostname = "${var.graphite_hostname}"
  graphite_port = "${var.graphite_port}"
  graphite_enabled = "${var.graphite_enabled}"
  env_vars = "${var.aquila-trainer["environment_overrides"]}"
}
