using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GuidTestApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class GuidController : ControllerBase
    {
        private readonly ILogger<GuidController> _logger;
        private readonly static Guid Guid = Guid.NewGuid();

        public GuidController(ILogger<GuidController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public string Get()
        {
            return Guid.ToString();
        }
    }
}
