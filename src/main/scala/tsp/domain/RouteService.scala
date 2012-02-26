package tsp.domain

class RouteService private (finder: RouteFinder) {

  def findRoute(routeStr: String): Either[NoSuchRouteException, Route] = {
    try {
        Right(finder.find(routeStr))
    }
    catch {
      case e: NoSuchRouteException => Left(e) 
    }
  }
  
  def findRoutesWithMaxStops(origin: Char, destination: Char, maxStops: Int): List[Route] = {
    finder.find(origin, destination)(route => route.legs.length <= maxStops)
  }
  
  def findRoutesWithStops(origin: Char, destination: Char, stops: Int): List[Route] = {
    finder.find(origin, destination) {
      route => route.legs.length <= stops
    } filter {
      route => route.legs.length == stops
    }
  }
  
  def findRoutesShorterThan(origin: Char, destination: Char, distance: Int): List[Route] = {
    finder.find(origin, destination) {
      route => route.distance < distance
    }
  }
  
  def findShortestRoute(origin: Char, destination: Char): Option[Route] = {
    finder.find(origin, destination) {
      route => route.legs.length <= finder.legs.length
    } sortWith {
      (r1, r2) => r1.distance < r2.distance
    } match {
      case r :: rs1 => Some(r)
      case _ => None
    }
  }
  
}

object RouteService {
  
    def apply(legs: String*): RouteService = new RouteService(RouteFinder(legs: _*))
    
}
