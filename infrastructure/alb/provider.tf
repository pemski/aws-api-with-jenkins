provider "aws" {
  profile = "default"
  region  = "eu-west-1"
  version = "~> 2.0"
}

terraform {
  backend "s3" {
    bucket = "psp-tf-state"
    key    = "aws-guid-api/alb"
    region = "eu-west-1"
  }
}
