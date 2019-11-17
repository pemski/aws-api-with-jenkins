variable "package_filename" {
  type    = string
  default = "../../guid-lambda.zip"
}

variable "function_handler" {
  type    = string
  default = "GuidGetter::GuidGetter.GuidGetter::Get"
}
