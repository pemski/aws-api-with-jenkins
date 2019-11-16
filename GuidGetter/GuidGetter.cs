using Amazon.Lambda.Core;
using System;

namespace GuidGetter
{
    public class GuidGetter
    {
        [LambdaSerializer(typeof(Amazon.Lambda.Serialization.Json.JsonSerializer))]
        public string Get()
        {
            return Guid.NewGuid().ToString();
        }
    }
}
