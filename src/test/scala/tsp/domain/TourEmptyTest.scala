package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TourEmptyTest extends FunSuite {

  val tour = Tour();
  
  test("length should return zero") {
    assert(tour.length === 0)
  }
  
  test("origin should result in IllegalStateException") {
    assertISE(tour.origin)
  }
  
  test("destination should result in IllegalStateException") {
    assertISE(tour.destination)
  }
  
  test("startsAt A should return false") {
    assert(tour.startsAt('A') === false)
  }
  
  test("endsAt B should return false") {
    assert(tour.endsAt('B') === false)
  }
  
  test("isLinkableTo AB1 should return true") {
    assert(tour.isLinkableTo("AB1") === true)
  }
  
  test("appending AB should return new tour containing AB") {
    expect(true) {
      tour.append("AB1").edges.toList match {
        case List(Edge(Path('A', 'B'), 1)) => true
        case _ => false
      }
    }
  }
  
  test("toString should return blank") {
    assert(tour.toString === "")
  }
  
  private def assertISE(testcase: => Unit) {
    intercept[IllegalStateException] {
      testcase
    }
  }
  
}
