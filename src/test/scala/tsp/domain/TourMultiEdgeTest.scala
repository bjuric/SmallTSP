package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TourMultiEdgeTest extends FunSuite with BeforeAndAfterEach {

  val edgeAB = Edge("AB1")
  val edgeBA = Edge("BA2")
  val edgeAC = Edge("AC3")
  val edgeCE = Edge("CE4")
  val edgeEF = Edge("EF5")
  val edgeDA = Edge("DA6")
   
  var tour: Tour = _ 
  
  override def beforeEach = {
    tour = Tour(edgeAB).append(edgeBA).append(edgeAC).append(edgeCE)
  }
  
  test("length should return 10") {
    assert(tour.length === 10)
  }
  
  test("origin should return A") {
    assert(tour.origin === 'A')
  }
  
  test("destination should return E") {
    assert(tour.destination === 'E')
  }
  
  test("startsAt A should return true") {
    assert(tour.startsAt('A') === true)
  }
  
  test("startsAt B should return false") {
    assert(tour.startsAt('B') === false)
  }
  
  test("endsAt E should return true") {
    assert(tour.endsAt('E') === true)
  }
  
  test("endsAt A should return false") {
    assert(tour.endsAt('A') === false)
  }
  
  test("isLinkableTo EF should return true") {
    assert(tour.isLinkableTo(edgeEF) === true)
  }
  
  test("isLinkableTo DA should return false") {
    assert(tour.isLinkableTo(edgeDA) === false)
  }
  
  test("appending EF should result in ABACEF") {
    tour = tour.append(edgeEF)
    assert(tour.edges.length === 5)
    assert(tour.edges(0) === edgeAB)
    assert(tour.edges(1) === edgeBA)
    assert(tour.edges(2) === edgeAC)
    assert(tour.edges(3) === edgeCE)
    assert(tour.edges(4) === edgeEF)
    assert(tour.startsAt('A') === true)
    assert(tour.endsAt('F') === true)
    assert(tour.length === 15)
    assert(tour.toString === "ABACEF")
  }
  
  test("toString should return ABACE") {
    assert(tour.toString === "ABACE")
  }
  
}
