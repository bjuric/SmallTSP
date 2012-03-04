package tsp.solver

import tsp.domain._

class TourSolver private (service: TourService) {

  def solve: List[(String, Any)] = {
    List(
      (" 1. Length of tour ABC", lengthOf { service.parseTour("ABC") } ),
      (" 2. Length of tour AD", lengthOf { service.parseTour("AD") } ),
      (" 3. Length of tour ADC", lengthOf { service.parseTour("ADC") } ),
      (" 4. Length of tour AEBCD", lengthOf { service.parseTour("AEBCD") } ),
      (" 5. Length of tour AED", lengthOf { service.parseTour("AED") } ),
      (" 6. Tours from C to C having at most 3 edges", stringResult { service.tourWithMaxEdgeCount('C', 'C', 3) } ),
      (" 7. Tours from A to C having exactly 4 edges", stringResult { service.tourWithExactEdgeCount('A', 'C', 4) } ),
      (" 8. Shortest tour from A to C", stringResult { service.shortestTour('A', 'C') } ),
      (" 9. Shortest tour from B to B", stringResult { service.shortestTour('B', 'B') } ),
      ("10. Tours from C to C having length less than 30", stringResult { service.tourShorterThan('C', 'C', 30) } ))
  }
  
  def lengthOf(result: Either[NoSuchPathException, Tour]) = result match {
    case Right(r) => r.length
    case Left(e) => "No such tour: "+ e.getMessage
  }
  
  def stringResult(result: List[Tour]) = result match {
    case x :: xs => result mkString ", "
    case _ => "None"
  }
  
  def stringResult(result: Option[Tour]) = result getOrElse "None"

}

object TourSolver {

  def apply(graph: String*): TourSolver = new TourSolver(TourService(graph: _*))

}
