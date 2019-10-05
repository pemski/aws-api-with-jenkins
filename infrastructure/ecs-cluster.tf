resource "aws_ecs_cluster" "webapi" {
  name = "${local.resource_prefix}_webapi"
  tags = local.tags
}
