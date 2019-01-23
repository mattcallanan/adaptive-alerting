variable "kubectl_context_name" {}
variable "kafka_hostname" {}
variable "kafka_port" {}
variable "graphite_hostname" {}
variable "graphite_port" {}
variable "graphite_enabled" {}
variable "kubectl_executable_name" {}
variable "app_namespace" {}
variable "node_selector_label"{}
variable "aa_cname"{}

# TODO Why is this part of Adaptive Alerting?
variable "metrictank" {
  type = "map"
}

variable "alerting" {
  type = "map"
}

variable "ad-mapper" {
  type = "map"
}

variable "ad-manager" {
  type = "map"
}

/*
variable "mc-a2m-mapper" {
  type = "map"
}
*/

variable "modelservice" {
  type = "map"
}

variable "notifier" {
  type = "map"
}

variable "aquila-detector" {
  type = "map"
}

variable "aquila-trainer" {
  type = "map"
}
