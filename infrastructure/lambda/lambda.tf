resource "aws_lambda_function" "guid_api" {
  function_name    = "${local.resource_prefix}_guid-api"
  filename         = var.package_filename
  role             = aws_iam_role.iam_for_lambda.arn
  handler          = var.function_handler
  source_code_hash = filebase64sha256(var.package_filename)
  runtime          = "dotnetcore2.1"
  tags             = local.tags
}

resource "aws_iam_role" "iam_for_lambda" {
  name = "${local.resource_prefix}_guid-api_lambda"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_policy" "guid_api_lambda_logging" {
  name        = "${local.resource_prefix}_guid-api_lambda_logging"
  description = "IAM policy for logging from the guid api lambda"

  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*",
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "attach_lambda_logging" {
  role       = aws_iam_role.iam_for_lambda.name
  policy_arn = aws_iam_policy.guid_api_lambda_logging.arn
}
