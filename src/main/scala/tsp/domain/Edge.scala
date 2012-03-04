package tsp.domain

case class Edge private(path: Path, length: Int)
 {
  
  assert (length > 0, "length must be greater than zero: "+ toString)
  
  def origin = path.origin
  def destination = path.destination
  def isLinkableTo(edge: Edge) = destination == edge.origin
  def startsAt(origin: Char) = this.origin == origin
  def endsAt(destination: Char) = this.destination == destination
  override def toString = path + length.toString
  
}

object Edge {
	
  private val EdgeRE = """^([A-Z]{2})(\d+)$""".r
  
  implicit def apply(edgeStr: String): Edge = {
    edgeStr match {
      case EdgeRE(path, length) => Edge(path, length.toInt)
      case _ => throw new IllegalArgumentException(edgeStr +" does not match "+ EdgeRE)
    }
  }
  
}
