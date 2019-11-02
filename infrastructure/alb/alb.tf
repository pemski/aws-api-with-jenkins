resource "aws_lb" "endpoint" {
  name               = "${local.resource_prefix}-guid-api"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb-guid-api.id]
  subnets            = data.aws_subnet_ids.main.ids

  enable_deletion_protection = false

  tags = local.tags
}

data "aws_subnet_ids" "main" {
  vpc_id = local.vpc_id
}
