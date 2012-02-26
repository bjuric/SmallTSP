package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteFinderTest extends FunSuite {

  val legAB5 = Leg("AB5")
  val legBC4 = Leg("BC4")
  val legCD8 = Leg("CD8")
  val legDC8 = Leg("DC8")
  val legDE6 = Leg("DE6")
  val legAD5 = Leg("AD5")
  val legCE2 = Leg("CE2")
  val legEB3 = Leg("EB3")
  val legAE7 = Leg("AE7")
  
  val legs = Set[Leg](legAB5, legBC4, legCD8, legDC8, legDE6, legAD5, legCE2, legEB3, legAE7)
  val finder = RouteFinder(legs)
  
  test("route finder with duplicate CD leg should result in AssertionError") {
	val legCD3 = Leg("CD3")
	intercept[AssertionError] {
      RouteFinder(legs + legCD3)
    }
  }
  
  test("string ABC should result in route having distance 9") {
    val route = finder.find("ABC")
    assert(route.legs.length === 2)
    assert(route.legs(0) === legAB5)
    assert(route.legs(1) === legBC4)
    assert(route.distance === 9)
  }
  
  test("string AD should result in route having distance 5") {
    val route = finder.find("AD")
    assert(route.legs.length === 1)
    assert(route.legs(0) === legAD5)
    assert(route.distance === 5)
  }
  
  test("string ADC should result in route having distance 13") {
    val route = finder.find("ADC")
    assert(route.legs.length === 2)
    assert(route.legs(0) === legAD5)
    assert(route.legs(1) === legDC8)
    assert(route.distance === 13)
  }
  
  test("string AEBCD should result in route having distance 22") {
    val route = finder.find("AEBCD")
    assert(route.legs.length === 4)
    assert(route.legs(0) === legAE7)
    assert(route.legs(1) === legEB3)
    assert(route.legs(2) === legBC4)
    assert(route.legs(3) === legCD8)
    assert(route.distance === 22)
  }
  
  test("string AED should result in NoSuchRouteException") {
    intercept[NoSuchRouteException] {
      finder.find("AED")
    }
  }
  
  test("2 routes should result from C to C having at most 3 stops") {
    val routes = finder.find('C', 'C') {
      route => route.legs.length <= 3
    }
    assert(routes.length === 2)
    assertContains(routes, "CEBC")
    assertContains(routes, "CDC")
  }
  
  test("6 routes should result from A to C having at most 4 stops") {
    val routes = finder.find('A', 'C') {
      route => route.legs.length <= 4
    } 
    assert(routes.length === 6)
    assertContains(routes, "ADC")
    assertContains(routes, "ADCDC")
    assertContains(routes, "ADEBC")
    assertContains(routes, "ABC")
    assertContains(routes, "ABCDC")
    assertContains(routes, "AEBC")
  }
  
  test("18 routes should result from B to B having at most 9 stops") {
	val routes = finder.find('B', 'B') {
      route => route.legs.length <= finder.legs.length
    }
    assert(routes.length === 18)
    assertContains(routes, "BCEB")
    assertContains(routes, "BCEBCEB")
    assertContains(routes, "BCEBCEBCEB")
    assertContains(routes, "BCEBCDCEB")
    assertContains(routes, "BCEBCDCDEB")
    assertContains(routes, "BCEBCDEB")
    assertContains(routes, "BCDCEB")
    assertContains(routes, "BCDCEBCEB")
    assertContains(routes, "BCDCEBCDEB")
    assertContains(routes, "BCDCDCEB")
    assertContains(routes, "BCDCDCDCEB")
    assertContains(routes, "BCDCDCDEB")
    assertContains(routes, "BCDCDEB")
    assertContains(routes, "BCDCDEBCEB")
    assertContains(routes, "BCDEB")
    assertContains(routes, "BCDEBCEB")
    assertContains(routes, "BCDEBCDCEB")
    assertContains(routes, "BCDEBCDEB")
  }

  test("7 routes should result from C to C having distance shorter than 30") {
	val routes = finder.find('C', 'C') {
      route => route.distance < 30
    }
    assert(routes.length === 7)
    assertContains(routes, "CEBC")
    assertContains(routes, "CEBCEBC")
    assertContains(routes, "CEBCEBCEBC")
    assertContains(routes, "CEBCDC")
    assertContains(routes, "CDC")
    assertContains(routes, "CDCEBC")
    assertContains(routes, "CDEBC")
  }
  
  private def assertContains(routes: List[Route], routeStr: String) {
    assert(routes.mkString.contains(routeStr) === true, "expected route "+ routeStr +" not found in result")
  }

}
