package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TourFinderTest extends FunSuite {

  val edgeAB5 = Edge("AB5")
  val edgeBC4 = Edge("BC4")
  val edgeCD8 = Edge("CD8")
  val edgeDC8 = Edge("DC8")
  val edgeDE6 = Edge("DE6")
  val edgeAD5 = Edge("AD5")
  val edgeCE2 = Edge("CE2")
  val edgeEB3 = Edge("EB3")
  val edgeAE7 = Edge("AE7")
  
  val edges = Set[Edge](edgeAB5, edgeBC4, edgeCD8, edgeDC8, edgeDE6, edgeAD5, edgeCE2, edgeEB3, edgeAE7)
  val finder = TourFinder(edges)
  
  test("tour finder with duplicate CD edge should result in AssertionError") {
	val edgeCD3 = Edge("CD3")
	intercept[AssertionError] {
      TourFinder(edges + edgeCD3)
    }
  }
  
  test("string ABC should result in tour having length 9") {
    val tour = finder.find("ABC")
    assert(tour.edges.length === 2)
    assert(tour.edges(0) === edgeAB5)
    assert(tour.edges(1) === edgeBC4)
    assert(tour.length === 9)
  }
  
  test("string AD should result in tour having length 5") {
    val tour = finder.find("AD")
    assert(tour.edges.length === 1)
    assert(tour.edges(0) === edgeAD5)
    assert(tour.length === 5)
  }
  
  test("string ADC should result in tour having length 13") {
    val tour = finder.find("ADC")
    assert(tour.edges.length === 2)
    assert(tour.edges(0) === edgeAD5)
    assert(tour.edges(1) === edgeDC8)
    assert(tour.length === 13)
  }
  
  test("string AEBCD should result in tour having length 22") {
    val tour = finder.find("AEBCD")
    assert(tour.edges.length === 4)
    assert(tour.edges(0) === edgeAE7)
    assert(tour.edges(1) === edgeEB3)
    assert(tour.edges(2) === edgeBC4)
    assert(tour.edges(3) === edgeCD8)
    assert(tour.length === 22)
  }
  
  test("string AED should result in NoSuchPathException") {
    intercept[NoSuchPathException] {
      finder.find("AED")
    }
  }
  
  test("2 tours should result from C to C having at most 3 edges") {
    val tours = finder.find('C', 'C') {
      tour => tour.edges.length <= 3
    }
    assert(tours.length === 2)
    assertContains(tours, "CEBC")
    assertContains(tours, "CDC")
  }
  
  test("6 tours should result from A to C having at most 4 edges") {
    val tours = finder.find('A', 'C') {
      tour => tour.edges.length <= 4
    } 
    assert(tours.length === 6)
    assertContains(tours, "ADC")
    assertContains(tours, "ADCDC")
    assertContains(tours, "ADEBC")
    assertContains(tours, "ABC")
    assertContains(tours, "ABCDC")
    assertContains(tours, "AEBC")
  }
  
  test("18 tours should result from B to B having at most 9 edges") {
	val tours = finder.find('B', 'B') {
      tour => tour.edges.length <= finder.graph.length
    }
    assert(tours.length === 18)
    assertContains(tours, "BCEB")
    assertContains(tours, "BCEBCEB")
    assertContains(tours, "BCEBCEBCEB")
    assertContains(tours, "BCEBCDCEB")
    assertContains(tours, "BCEBCDCDEB")
    assertContains(tours, "BCEBCDEB")
    assertContains(tours, "BCDCEB")
    assertContains(tours, "BCDCEBCEB")
    assertContains(tours, "BCDCEBCDEB")
    assertContains(tours, "BCDCDCEB")
    assertContains(tours, "BCDCDCDCEB")
    assertContains(tours, "BCDCDCDEB")
    assertContains(tours, "BCDCDEB")
    assertContains(tours, "BCDCDEBCEB")
    assertContains(tours, "BCDEB")
    assertContains(tours, "BCDEBCEB")
    assertContains(tours, "BCDEBCDCEB")
    assertContains(tours, "BCDEBCDEB")
  }

  test("7 tours should result from C to C having length shorter than 30") {
	val tours = finder.find('C', 'C') {
      tour => tour.length < 30
    }
    assert(tours.length === 7)
    assertContains(tours, "CEBC")
    assertContains(tours, "CEBCEBC")
    assertContains(tours, "CEBCEBCEBC")
    assertContains(tours, "CEBCDC")
    assertContains(tours, "CDC")
    assertContains(tours, "CDCEBC")
    assertContains(tours, "CDEBC")
  }
  
  private def assertContains(tours: List[Tour], tourStr: String) {
    assert(tours.mkString.contains(tourStr) === true, "expected tour "+ tourStr +" not found in result")
  }

}
