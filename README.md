# HttpServer

## Task
The objective is to implement framework-like functionality to support
[JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services) API.

The code needs to parse the provided classes in `MyHttpServer#register` method using
reflection and create appropriate contexts with the proper method invocation.

Get inspired by the actual usage in already existing projects like:
<https://github.com/oracle/opengrok/tree/master/opengrok-web/src/main/java/org/opengrok/web/api/v1/controller>

For JSON processing please use Gson or Jackson libraries.

Please also write additional unit tests.