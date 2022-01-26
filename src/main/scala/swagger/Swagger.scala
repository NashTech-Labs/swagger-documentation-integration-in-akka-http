package swagger

import akka.actor.ActorSystem
import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import com.typesafe.config.{Config, ConfigFactory}
import io.swagger.models.Scheme
import io.swagger.models.auth.ApiKeyAuthDefinition
import io.swagger.models.auth.In.QUERY

case class Swagger(system: ActorSystem) extends SwaggerHttpService {
  val config: Config = ConfigFactory.load()
  val API_URL: String = config.getString("swagger.api.url")
  val BASE_PATH: String = config.getString("swagger.api.base.path")
  val PROTOCOL: String = config.getString("swagger.api.protocol")

  override def apiClasses: Set[Class[_]] = Set(classOf[Base],
  classOf[EmployeeDetails])

  override val host: String = API_URL

  override val basePath: String = BASE_PATH

  override def schemes: List[Scheme] = List(Scheme.forValue(PROTOCOL))

  override def apiDocsPath: String = "api-docs"

  val apiKey = new ApiKeyAuthDefinition("api_key", QUERY)

  override val securitySchemeDefinitions: Map[String, ApiKeyAuthDefinition] = Map("apiKey" -> apiKey)

  override def info: Info =
    Info(
      "Swagger Akka HTTP Demo Application...",
      "1.0",
      "Swagger API",
      "",
      None,
      None,
      Map.empty)
}
