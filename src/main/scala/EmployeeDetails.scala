import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Route, StandardRoute}

case class EmployeeDetails(system: ActorSystem) {
  val routes: Route = path("ping") {
    get {
      complete(HttpResponse(OK, entity = "pong"))
    }
  } ~
    path("employee" / "details" / Segment) { employeeId =>
      get {
        fetchEmployeeDetailsById(employeeId)
      }

    }

  def fetchEmployeeDetailsById(id: String): StandardRoute = {
    id match {
      case "101" => complete(HttpResponse(OK, entity = "Employee name: Abhinav , Address: Delhi"))
      case "102" => complete(HttpResponse(OK, entity = "Employee name: Amit , Address: Noida"))
      case _ => complete(HttpResponse(NotFound, entity = "No employee found with this id"))

    }
  }
}