package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LegTest extends FunSuite {

  val legAB: Leg = "AB1"
  val legBC: Leg = "BC2"
  val legCD: Leg = "CD3"
  
  test("path on leg AB should return AB") {
    assert(legAB.path === Path("AB"))
  }
  
  test("distance on leg of distance 1 should return 1") {
    assert(legAB.distance === 1)
  }
  
  test("origin on leg AB should return A") {
    assert(legAB.origin === 'A')
  }
  
  test("destination on leg AB should return B") {
    assert(legAB.destination === 'B')
  }
  
  test("leg with distance = 0 should result in AE") {
    intercept[AssertionError] {
      Leg("AB0")
    }
  }
  
  test("leg with distance < 0 should result in IAE") {
    intercept[IllegalArgumentException] {
      Leg("AB-1")
    }
  }
  
  test("leg AB should be linkable to BC") {
    assert(legAB.isLinkableTo(legBC) === true)
  }
  
  test("leg AB should not be linkable to CD") {
    assert(legAB.isLinkableTo(legCD) === false)
  }
  
  test("startsAt A on leg AB should return true") {
    assert(legAB.startsAt('A') === true)
  }
  
  test("startsAt B on leg AB should return false") {
    assert(legAB.startsAt('B') === false)
  }
  
  test("endsAt B on leg AB should return true") {
    assert(legAB.endsAt('B') === true)
  }
  
  test("endsAt A on leg AB should return false") {
    assert(legAB.endsAt('A') === false)
  }
  
  test("string AB1 should implicitly convert to leg AB with distance 1") {
    val leg: Leg= "AB1"
    assert(leg.path === Path("AB"))
    assert(leg.distance === 1)
  }
  
  test("invalid leg strings should result in IAE") {
    assertInvalid("")
    assertInvalid("A")
    assertInvalid("ABC")
    assertInvalid("1B")
    assertInvalid("A1")
    assertInvalid("11")
    assertInvalid("#$")
  }
  
  private def assertInvalid(legString: String) {
    intercept[IllegalArgumentException] {
      Leg(legString)
    }
  }
  
  test("leg with same origin and destination should result in AE") {
    intercept[AssertionError] {
      Leg("AA1")
    }
  }
  
  test("toString on leg AB with distance 1 should return AB1") {
    val leg = Leg("AB1")
    assert(leg.toString === "AB1")
  }
  
}
