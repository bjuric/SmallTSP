package tsp.solver

import org.junit.runner.RunWith

import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RouteSolverTest extends FunSuite {

  val solver = RouteSolver("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
  
  test("standard known data fixture should yield expected results") {
    
    val results = solver.solve
    assert(results.length === 10)
    
    val iter = results.elements
    assert(iter.next._2 === 9)
    assert(iter.next._2 === 5)
    assert(iter.next._2 === 13)
    assert(iter.next._2 === 22)
    assert(iter.next._2 === "No such route: Path ED does not exist")
    assert(iter.next._2.toString === "CDC, CEBC")
    assert(iter.next._2.toString === "ABCDC, ADEBC, ADCDC")
    assert(iter.next._2.toString === "ABC")
    assert(iter.next._2.toString === "BCEB")
    assert(iter.next._2.toString === "CDEBC, CDC, CDCEBC, CEBC, CEBCDC, CEBCEBC, CEBCEBCEBC")  
    
  }
  
}
