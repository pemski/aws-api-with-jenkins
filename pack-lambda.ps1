dotnet tool install -g Amazon.Lambda.Tools
dotnet tool update -g Amazon.Lambda.Tools

dotnet lambda package guid-lambda.zip -pl GuidGetter -c Release -f netcoreapp2.1 
