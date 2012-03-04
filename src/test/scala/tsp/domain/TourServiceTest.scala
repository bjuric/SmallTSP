package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TourServiceTest extends FunSuite {

  val service = TourService("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
  
  test("2 tours should result from C to C having at most 3 edges") {
    assert(service.tourWithMaxEdgeCount('C', 'C', 3).mkString(", ") === "CDC, CEBC")
  }
  
  test("5 tours should result from A to B having at most 4 edges") {
    assert(service.tourWithMaxEdgeCount('A', 'B', 4).mkString(", ") === "AB, ABCEB, ADEB, ADCEB, AEB")
  }
  
  test("1 tour should result from A to E having at most 1 edge") {
    assert(service.tourWithMaxEdgeCount('A', 'E', 1).mkString(", ") === "AE")
  }
  
  test("0 tours should result from C to A having at most 10 edges") {
    assert(service.tourWithMaxEdgeCount('C', 'A', 10).size === 0)
  }
  
  test("1 tour should result from A to E having exactly 1 edge") {
    assert(service.tourWithExactEdgeCount('A', 'E', 1).mkString(", ") === "AE")
  }
  
  test("0 tours should result from E to D having exactly 1 edge") {
    assert(service.tourWithExactEdgeCount('E', 'D', 1).size === 0)
  }
  
  test("7 tours should result from C to C having length shorter than 30") {
	assert(service.tourShorterThan('C', 'C', 30).mkString(", ") === "CDEBC, CDC, CDCEBC, CEBC, CEBCDC, CEBCEBC, CEBCEBCEBC")
  }
  
  test("1 tour should result from A to E having length shorter than 8") {
	assert(service.tourShorterThan('A', 'E', 8).mkString(", ") === "AE")
  }
  
  test("0 tours should result from A to E having length shorter than 7") {
	assert(service.tourShorterThan('A', 'E', 7).size === 0)
  }
  
  test("shortest tour from A to C should have length of 9") {
    assert(service.shortestTour('A', 'C').toString === "Some(ABC)")
  }
  
  test("shortest tour from B to B should have length of 9") {
    assert(service.shortestTour('B', 'B').toString === "Some(BCEB)")
  }
  
  test("shortest tour from A to E should have length of 7") {
    assert(service.shortestTour('A', 'E').toString === "Some(AE)")
  }
  
  test("shortest tour from C to A should result in None") {
   	assert(service.shortestTour('C', 'A') === None)
  }
  
  test("shortest tour from D to A should result in None") {
   	assert(service.shortestTour('D', 'A') === None)
  }
  
}
