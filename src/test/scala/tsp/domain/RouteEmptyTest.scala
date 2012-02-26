package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteEmptyTest extends FunSuite {

  val route = Route();
  
  test("distance should return zero") {
    assert(route.distance === 0)
  }
  
  test("origin should result in ISE") {
    assertISE(route.origin)
  }
  
  test("destination should result in ISE") {
    assertISE(route.destination)
  }
  
  test("startsAt A should return false") {
    assert(route.startsAt('A') === false)
  }
  
  test("endsAt B should return false") {
    assert(route.endsAt('B') === false)
  }
  
  test("isLinkableTo AB1 should return true") {
    assert(route.isLinkableTo("AB1") === true)
  }
  
  test("appending AB should return new route containing AB") {
    expect(true) {
      route.append("AB1").legs.toList match {
        case List(Leg(Path('A', 'B'), 1)) => true
        case _ => false
      }
    }
  }
  
  test("toString should return blank") {
    assert(route.toString === "")
  }
  
  private def assertISE(testcase: => Unit) {
    intercept[IllegalStateException] {
      testcase
    }
  }
  
}
