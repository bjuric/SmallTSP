package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteServiceTest extends FunSuite {

  val service = RouteService("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
  
  test("2 routes should result from C to C having at most 3 stops") {
    assert(service.findRoutesWithMaxStops('C', 'C', 3).mkString(", ") === "CDC, CEBC")
  }
  
  test("5 routes should result from A to B having at most 4 stops") {
    assert(service.findRoutesWithMaxStops('A', 'B', 4).mkString(", ") === "AB, ABCEB, ADEB, ADCEB, AEB")
  }
  
  test("1 route should result from A to E having at most 1 stop") {
    assert(service.findRoutesWithMaxStops('A', 'E', 1).mkString(", ") === "AE")
  }
  
  test("0 routes should result from C to A having at most 10 stops") {
    assert(service.findRoutesWithMaxStops('C', 'A', 10).size === 0)
  }
  
  test("1 route should result from A to E having exactly 1 stop") {
    assert(service.findRoutesWithStops('A', 'E', 1).mkString(", ") === "AE")
  }
  
  test("0 routes should result from E to D having exactly 1 stop") {
    assert(service.findRoutesWithStops('E', 'D', 1).size === 0)
  }
  
  test("7 routes should result from C to C having distance shorter than 30") {
	assert(service.findRoutesShorterThan('C', 'C', 30).mkString(", ") === "CDEBC, CDC, CDCEBC, CEBC, CEBCDC, CEBCEBC, CEBCEBCEBC")
  }
  
  test("1 route should result from A to E having distance shorter than 8") {
	assert(service.findRoutesShorterThan('A', 'E', 8).mkString(", ") === "AE")
  }
  
  test("0 routes should result from A to E having distance shorter than 7") {
	assert(service.findRoutesShorterThan('A', 'E', 7).size === 0)
  }
  
  test("shortest route from A to C should have distance of 9") {
    assert(service.findShortestRoute('A', 'C').toString === "Some(ABC)")
  }
  
  test("shortest route from B to B should have distance of 9") {
    assert(service.findShortestRoute('B', 'B').toString === "Some(BCEB)")
  }
  
  test("shortest route from A to E should have distance of 7") {
    assert(service.findShortestRoute('A', 'E').toString === "Some(AE)")
  }
  
  test("shortest route from C to A should result in None") {
   	assert(service.findShortestRoute('C', 'A') === None)
  }
  
  test("shortest route from D to A should result in None") {
   	assert(service.findShortestRoute('D', 'A') === None)
  }
  
}
