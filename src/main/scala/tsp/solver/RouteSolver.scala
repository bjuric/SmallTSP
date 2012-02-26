package tsp.solver

import tsp.domain._

class RouteSolver private (service: RouteService) {

  def solve: List[(String, Any)] = {
    List(
      (" 1. Distance of route ABC", distanceOf { service.findRoute("ABC") } ),
      (" 2. Distance of route AD", distanceOf { service.findRoute("AD") } ),
      (" 3. Distance of route ADC", distanceOf { service.findRoute("ADC") } ),
      (" 4. Distance of route AEBCD", distanceOf { service.findRoute("AEBCD") } ),
      (" 5. Distance of route AED", distanceOf { service.findRoute("AED") } ),
      (" 6. Routes from C to C having at most 3 stops", routesOf { service.findRoutesWithMaxStops('C', 'C', 3) } ),
      (" 7. Routes from A to C having exactly 4 stops", routesOf { service.findRoutesWithStops('A', 'C', 4) } ),
      (" 8. Shortest route from A to C", routeOf { service.findShortestRoute('A', 'C') } ),
      (" 9. Shortest route from B to B", routeOf { service.findShortestRoute('B', 'B') } ),
      ("10. Routes from C to C having distance less than 30", routesOf { service.findRoutesShorterThan('C', 'C', 30) } ))
  }
  
  def distanceOf(result: Either[NoSuchRouteException, Route]) = result match {
    case Right(r) => r.distance
    case Left(e) => "No such route: "+ e.getMessage
  }
  
  def routesOf(result: List[Route]) = result match {
    case x :: xs => result mkString ", "
    case _ => "None"
  }
  
  def routeOf(result: Option[Route]) = result getOrElse "None"

}

object RouteSolver {

  def apply(legs: String*): RouteSolver = new RouteSolver(RouteService(legs: _*))

}
