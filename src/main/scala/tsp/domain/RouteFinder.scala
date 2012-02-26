package tsp.domain

class RouteFinder private (val legs: List[Leg]) {

  legs.foreach {
    leg =>
      val paths = legs filter { _.path == leg.path }
      assert(paths.size == 1, "Cannot accept " + paths.size + " legs with same path: " + paths.mkString(", "))
  }

  def find(origin: Char, destination: Char)(condition: Route => Boolean): List[Route] = {
      find(Route(), condition, _ startsAt origin, _ endsAt destination)
  }

  private def find(current: Route, condition: Route => Boolean, linkable: Leg => Boolean, found: Route => Boolean): List[Route] = {
    legs filter { linkable } map { current append _ } filter { condition(_) } flatMap {
      route =>
        val routes = find(route, condition, route isLinkableTo _, found)
        if (found(route)) route :: routes
        else routes
    }
  }
  
  def find(routeStr: String): Route = {
    import RouteFinder.RouteStrRE
    routeStr match {
      case RouteStrRE(path) =>
        val route = Route(legWith(path))
        (route /: routeStr.drop(2)) {
          (r, dest) =>
            r append legWith(r.destination.toString + dest)
        }
      case _ => throw new IllegalArgumentException(routeStr + " does not match " + RouteStrRE)
    }
  }

  private def legWith(path: Path): Leg = {
    legs find { leg => leg.path == path } match {
      case Some(x) => x
      case _ => throw new NoSuchRouteException(path)
    }
  }

}

object RouteFinder {

  private val RouteStrRE = "^([A-Z]{2})[A-Z]*$".r

  def apply(legRegister: Set[Leg]): RouteFinder = new RouteFinder(legRegister.toList)
  def apply(legs: String*): RouteFinder = apply((Set[Leg]() /: legs)(_+_))

}