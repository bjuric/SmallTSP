package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TourSingleEdgeTest extends FunSuite with BeforeAndAfterEach {

  val edgeAB = Edge("AB1")
  val edgeBA = Edge("BA1")
  
  var tour: Tour = _ 
  
  override def beforeEach = {
    tour = Tour(edgeAB)
  }
  
  test("length should return 1") {
    assert(tour.length === 1)
  }
  
  test("origin should return A") {
    assert(tour.origin === 'A')
  }
  
  test("destination should return B") {
    assert(tour.destination === 'B')
  }
  
  test("startsAt A should return true") {
    assert(tour.startsAt('A') === true)
  }
  
  test("startsAt B should return false") {
    assert(tour.startsAt('B') === false)
  }
  
  test("endsAt B should return true") {
    assert(tour.endsAt('B') === true)
  }
  
  test("endsAt A should return false") {
    assert(tour.endsAt('A') === false)
  }
  
  test("AB isLinkableTo BA should return true") {
    assert(tour.isLinkableTo(edgeBA) === true)
  }
  
  test("AB isLinkableTo AB should return false") {
    assert(tour.isLinkableTo(edgeAB) === false)
  }
  
  test("appending BA to existing AB should return tour with AB and BA") {
    tour = tour.append(edgeBA)
    assert(tour.edges.length === 2)
    assert(tour.edges(0) === edgeAB)
    assert(tour.edges(1) === edgeBA)
    assert(tour.length === 2)
    assert(tour.toString === "ABA")
  }
  
  test("toString should return AB") {
    assert(tour.toString === "AB")
  }
  
}
