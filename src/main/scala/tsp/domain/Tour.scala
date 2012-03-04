package tsp.domain

class Tour private (val edges: List[Edge]) {
  
  lazy val length: Int = edges map { _.length } sum
  
  def origin: Char = edges match { 
    case x :: _ => x.path.origin 
    case _ => throw new IllegalStateException("Tour has no edges")
  }
  
  def destination: Char = edges.lastOption match { 
    case Some(x) => x.path.destination
    case _ => throw new IllegalStateException("Tour has no edges")
  }
  
  def startsAt(origin: Char): Boolean = edges match { 
    case x :: _ => x.startsAt(origin) 
    case _ => false
  }
  
  def endsAt(destination: Char): Boolean = edges.lastOption match {
    case Some(x) => x.endsAt(destination) 
    case _ => false
  }
  
  def isLinkableTo(edge: Edge): Boolean = edges.lastOption match {
    case Some(x) => x.isLinkableTo(edge) 
    case _ => true
  }
  
  def append(edge: Edge): Tour = if (isLinkableTo(edge)) {
    new Tour(edges ::: List(edge))
  } else {
    throw new IllegalStateException(this +" is not linkable to "+ edge)
  }
  
  override def toString = edges.firstOption match {
    case Some(x) => x.origin + (edges map { _.destination.toString } mkString) 
    case _ => ""
  }
  
}

object Tour {
	
  def apply() = new Tour(List())
  def apply(edge:Edge) = new Tour(List(edge))
  
}
