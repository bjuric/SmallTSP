package tsp.domain

class Route private (val legs: List[Leg]) {
  
  lazy val distance: Int = legs map { _.distance } sum
  
  def origin: Char = legs match { 
    case x :: _ => x.path.origin 
    case _ => throw new IllegalStateException("Route has no legs")
  }
  
  def destination: Char = legs.lastOption match { 
    case Some(x) => x.path.destination
    case _ => throw new IllegalStateException("Route has no legs")
  }
  
  def startsAt(origin: Char): Boolean = legs match { 
    case x :: _ => x.startsAt(origin) 
    case _ => false
  }
  
  def endsAt(destination: Char): Boolean = legs.lastOption match {
    case Some(x) => x.endsAt(destination) 
    case _ => false
  }
  
  def isLinkableTo(leg: Leg): Boolean = legs.lastOption match {
    case Some(x) => x.isLinkableTo(leg) 
    case _ => true
  }
  
  def append(leg: Leg): Route = if (isLinkableTo(leg)) {
    new Route(legs ::: List(leg))
  } else {
    throw new IllegalStateException(this +" is not linkable to "+ leg)
  }
  
  override def toString = legs.headOption match {
    case Some(x) => x.origin + (legs map { _.destination.toString } mkString) 
    case _ => ""
  }
  
}

object Route {
	
  def apply() = new Route(List())
  def apply(leg:Leg) = new Route(List(leg))
  
}
