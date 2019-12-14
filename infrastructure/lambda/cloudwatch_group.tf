resource "aws_cloudwatch_log_group" "guid_api_lambda" {
  name              = "/guid-api/${local.resource_prefix}_lambda"
  retention_in_days = 1
  tags              = local.tags
}
