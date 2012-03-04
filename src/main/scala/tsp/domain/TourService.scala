package tsp.domain

class TourService private (finder: TourFinder) {

  def parseTour(tourStr: String): Either[NoSuchPathException, Tour] = {
    try {
        Right(finder.find(tourStr))
    }
    catch {
      case e: NoSuchPathException => Left(e) 
    }
  }
  
  def tourWithMaxEdgeCount(origin: Char, destination: Char, maxEdgeCount: Int): List[Tour] = {
    finder.find(origin, destination)(tour => tour.edges.length <= maxEdgeCount)
  }
  
  def tourWithExactEdgeCount(origin: Char, destination: Char, edgeCount: Int): List[Tour] = {
    finder.find(origin, destination) {
      tour => tour.edges.length <= edgeCount
    } filter {
      tour => tour.edges.length == edgeCount
    }
  }
  
  def tourShorterThan(origin: Char, destination: Char, length: Int): List[Tour] = {
    finder.find(origin, destination) {
      tour => tour.length < length
    }
  }
  
  def shortestTour(origin: Char, destination: Char): Option[Tour] = {
    finder.find(origin, destination) {
      tour => tour.edges.length <= finder.graph.length
    } sortWith {
      (t1, t2) => t1.length < t2.length
    } match {
      case t :: ts1 => Some(t)
      case _ => None
    }
  }
  
}

object TourService {
  
    def apply(graph: String*): TourService = new TourService(TourFinder(graph: _*))
    
}
