import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class EmployeeDetailsTest extends WordSpec with Matchers with ScalatestRouteTest {
  private val employeeDetails = EmployeeDetails(system)

  "EmployeeDetails" should {

    "be able to get response from the ping route" in {
      Get("/ping") ~> employeeDetails.routes ~> check {
        responseAs[String] should include("pong")
      }
    }

    "be able to get response from the employee details route by employee id 101" in {
      Get("/employee/details/101") ~> employeeDetails.routes ~> check {
        responseAs[String] should include("Employee name: Abhinav , Address: Delhi")
      }
    }

    "be able to get response from the employee details route by employee id 102" in {
      Get("/employee/details/102") ~> employeeDetails.routes ~> check {
        responseAs[String] should include("Employee name: Amit , Address: Noida")
      }
    }

    "be able to get response from the employee details route by invalid id" in {
      Get("/employee/details/1a1") ~> employeeDetails.routes ~> check {
        responseAs[String] should include("No employee found with this id")
      }
    }
  }
}
