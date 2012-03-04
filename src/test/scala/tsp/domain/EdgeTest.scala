package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EdgeTest extends FunSuite {

  val edgeAB: Edge = "AB1"
  val edgeBC: Edge = "BC2"
  val edgeCD: Edge = "CD3"
  
  test("path on edge AB should return AB") {
    assert(edgeAB.path === Path("AB"))
  }
  
  test("edge of length 1 should return 1") {
    assert(edgeAB.length === 1)
  }
  
  test("origin on edge AB should return A") {
    assert(edgeAB.origin === 'A')
  }
  
  test("destination on edge AB should return B") {
    assert(edgeAB.destination === 'B')
  }
  
  test("edge with length = 0 should result in AssertionError") {
    intercept[AssertionError] {
      Edge("AB0")
    }
  }
  
  test("edge with length < 0 should result in IllegalArgumentException") {
    intercept[IllegalArgumentException] {
      Edge("AB-1")
    }
  }
  
  test("edge AB should be linkable to BC") {
    assert(edgeAB.isLinkableTo(edgeBC) === true)
  }
  
  test("edge AB should not be linkable to CD") {
    assert(edgeAB.isLinkableTo(edgeCD) === false)
  }
  
  test("startsAt A on edge AB should return true") {
    assert(edgeAB.startsAt('A') === true)
  }
  
  test("startsAt B on edge AB should return false") {
    assert(edgeAB.startsAt('B') === false)
  }
  
  test("endsAt B on edge AB should return true") {
    assert(edgeAB.endsAt('B') === true)
  }
  
  test("endsAt A on edge AB should return false") {
    assert(edgeAB.endsAt('A') === false)
  }
  
  test("string AB1 should implicitly convert to edge AB with length 1") {
    val edge: Edge= "AB1"
    assert(edge.path === Path("AB"))
    assert(edge.length === 1)
  }
  
  test("invalid edge strings should result in IAE") {
    assertInvalid("")
    assertInvalid("A")
    assertInvalid("ABC")
    assertInvalid("1B")
    assertInvalid("A1")
    assertInvalid("11")
    assertInvalid("#$")
  }
  
  private def assertInvalid(edgeString: String) {
    intercept[IllegalArgumentException] {
      Edge(edgeString)
    }
  }
  
  test("edge with same origin and destination should result in AE") {
    intercept[AssertionError] {
      Edge("AA1")
    }
  }
  
  test("toString on edge AB with length 1 should return AB1") {
    val edge = Edge("AB1")
    assert(edge.toString === "AB1")
  }
  
}
