package simulations

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._

class ReqResSimulation extends Simulation {

  val protocol = karateProtocol()

  val testScenario = scenario("Get list of users")
    .exec(karateFeature("classpath:Apitest/feature/reqres.feature"))

  setUp(
    testScenario.inject(atOnceUsers(1)) // You can ramp this up
  ).protocols(protocol)
}
