package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteMultiLegTest extends FunSuite with BeforeAndAfterEach {

  val legAB = Leg("AB1")
  val legBA = Leg("BA2")
  val legAC = Leg("AC3")
  val legCE = Leg("CE4")
  val legEF = Leg("EF5")
  val legDA = Leg("DA6")
   
  var route: Route = _ 
  
  override def beforeEach = {
    route = Route(legAB).append(legBA).append(legAC).append(legCE)
  }
  
  test("distance should return 10") {
    assert(route.distance === 10)
  }
  
  test("origin should return A") {
    assert(route.origin === 'A')
  }
  
  test("destination should return E") {
    assert(route.destination === 'E')
  }
  
  test("startsAt A should return true") {
    assert(route.startsAt('A') === true)
  }
  
  test("startsAt B should return false") {
    assert(route.startsAt('B') === false)
  }
  
  test("endsAt E should return true") {
    assert(route.endsAt('E') === true)
  }
  
  test("endsAt A should return false") {
    assert(route.endsAt('A') === false)
  }
  
  test("isLinkableTo EF should return true") {
    assert(route.isLinkableTo(legEF) === true)
  }
  
  test("isLinkableTo DA should return false") {
    assert(route.isLinkableTo(legDA) === false)
  }
  
  test("appending EF should result in ABACEF") {
    route = route.append(legEF)
    assert(route.legs.length === 5)
    assert(route.legs(0) === legAB)
    assert(route.legs(1) === legBA)
    assert(route.legs(2) === legAC)
    assert(route.legs(3) === legCE)
    assert(route.legs(4) === legEF)
    assert(route.startsAt('A') === true)
    assert(route.endsAt('F') === true)
    assert(route.distance === 15)
    assert(route.toString === "ABACEF")
  }
  
  test("toString should return ABACE") {
    assert(route.toString === "ABACE")
  }
  
}
