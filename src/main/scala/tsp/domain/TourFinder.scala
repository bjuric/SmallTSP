package tsp.domain

class TourFinder private (val graph: List[Edge])
 {

  graph.foreach {
    edge =>
      val paths = graph filter { _.path == edge.path }
      assert(paths.size == 1, "Cannot accept " + paths.size + " edges with same path: " + paths.mkString(", "))
  }

  def find(origin: Char, destination: Char)(condition: Tour => Boolean): List[Tour] = {
      find(Tour(), condition, _ startsAt origin, _ endsAt destination)
  }

  private def find(current: Tour, condition: Tour => Boolean, linkable: Edge => Boolean, found: Tour => Boolean): List[Tour] = {
    graph filter { linkable } map { current append _ } filter { condition(_) } flatMap {
      tour =>
        val tours = find(tour, condition, tour isLinkableTo _, found)
        if (found(tour)) tour :: tours
        else tours
    }
  }
  
  def find(tourStr: String): Tour = {
    import TourFinder.TourStrRE
    tourStr match {
      case TourStrRE(path) =>
        val tour = Tour(edgeWith(path))
        (tour /: tourStr.drop(2)) {
          (t, dest) =>
            t append edgeWith(t.destination.toString + dest)
        }
      case _ => throw new IllegalArgumentException(tourStr + " does not match " + TourStrRE)
    }
  }

  private def edgeWith(path: Path): Edge = {
    graph find { edge => edge.path == path } match {
      case Some(x) => x
      case _ => throw new NoSuchPathException(path)
    }
  }

}

object TourFinder {

  private val TourStrRE = "^([A-Z]{2})[A-Z]*$".r

  def apply(graph: Set[Edge]): TourFinder = new TourFinder(graph.toList)
  def apply(graph: String*): TourFinder = apply((Set[Edge]() /: graph)(_+_))

}