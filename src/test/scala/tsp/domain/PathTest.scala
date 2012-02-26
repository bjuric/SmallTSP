package tsp.domain

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PathTest extends FunSuite {

  val pathAB = Path("AB")
                    
  test("origin on path AB should return A") {
    assert(pathAB.origin === 'A')
  }
  
  test("destination on path AB should return B") {
    assert(pathAB.destination === 'B')
  }
  
  test("string AB should implicitly convert to Path AB") {
    val path: Path = "AB"
    assert(path === pathAB)
  }
  
  test("invalid path strings should result in IAE") {
    assertInvalid("")
    assertInvalid("A")
    assertInvalid("a")
    assertInvalid("1")
    assertInvalid("ABC")
    assertInvalid("1B")
    assertInvalid("A1")
    assertInvalid("11")
    assertInvalid("#$")
  }
  
  private def assertInvalid(pathString: String) {
    intercept[IllegalArgumentException] {
      Path(pathString)
    }
  }
  
  test("path AA with same origin and destination should result in AE") {
    intercept[AssertionError] {
      Path("AA")
    }
  }
  
  test("toString on path AB should return AB") {
    assert(pathAB.toString === "AB")
  }
  
}
