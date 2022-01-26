package swagger

import akka.http.scaladsl.server.Route
import io.swagger.annotations.{Api, ApiImplicitParam, ApiImplicitParams, ApiOperation, ApiResponse, ApiResponses, SwaggerDefinition, Tag}
import javax.ws.rs.Path

@Path("/employee/details/{id}")
@Api(value = "/employee/details/{id}")
@SwaggerDefinition(tags = Array(new Tag(name = "employee details", description = "operations useful for managing Employee details")))
trait EmployeeDetails {
  @ApiOperation(value = "employee details", tags = Array("employee details"), httpMethod = "GET", notes = "This route will return a employee details")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(required = true, name = "id", dataType = "string", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK"),
    new ApiResponse(code = 500, message = "There was an internal server error."),
    new ApiResponse(code = 400, message = "Not Found")
  ))
  def getEmployeeDetails: Option[Route] = None
}