package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteSingleLegTest extends FunSuite with BeforeAndAfterEach {

  val legAB = Leg("AB1")
  val legBA = Leg("BA1")
  
  var route: Route = _ 
  
  override def beforeEach = {
    route = Route(legAB)
  }
  
  test("distance should return 1") {
    assert(route.distance === 1)
  }
  
  test("origin should return A") {
    assert(route.origin === 'A')
  }
  
  test("destination should return B") {
    assert(route.destination === 'B')
  }
  
  test("startsAt A should return true") {
    assert(route.startsAt('A') === true)
  }
  
  test("startsAt B should return false") {
    assert(route.startsAt('B') === false)
  }
  
  test("endsAt B should return true") {
    assert(route.endsAt('B') === true)
  }
  
  test("endsAt A should return false") {
    assert(route.endsAt('A') === false)
  }
  
  test("AB isLinkableTo BA should return true") {
    assert(route.isLinkableTo(legBA) === true)
  }
  
  test("AB isLinkableTo AB should return false") {
    assert(route.isLinkableTo(legAB) === false)
  }
  
  test("appending BA to existing AB should return route with AB and BA") {
    route = route.append(legBA)
    assert(route.legs.length === 2)
    assert(route.legs(0) === legAB)
    assert(route.legs(1) === legBA)
    assert(route.distance === 2)
    assert(route.toString === "ABA")
  }
  
  test("toString should return AB") {
    assert(route.toString === "AB")
  }
  
}
