import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import swagger.Swagger

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object Boot extends App {
    implicit val system: ActorSystem = ActorSystem("my-actor-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val routes: Route = EmployeeDetails(system).routes ~ Swagger(system).routes ~
      getFromResourceDirectory("swagger-ui")

    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "0.0.0.0", 8080)

    println("Application has started on port 8080")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

}