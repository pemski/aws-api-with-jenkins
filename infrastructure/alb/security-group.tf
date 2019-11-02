resource "aws_security_group" "alb-guid-api" {
  name   = "${local.resource_prefix}_alb_guid-api"
  vpc_id = local.vpc_id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = [var.ingress_cidr]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = merge(local.tags, { description = "Defines traffic allowed to and from endpoint ALB for guid api." })
}
