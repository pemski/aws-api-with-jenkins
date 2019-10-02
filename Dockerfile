FROM mcr.microsoft.com/dotnet/core/aspnet:3.0.0-alpine3.9

WORKDIR /app
COPY publish .

ENV ASPNETCORE_URLS http://*:80

EXPOSE 80

ENTRYPOINT ["dotnet", "GuidTestApi.dll"]
