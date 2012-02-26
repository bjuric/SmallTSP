package tsp.domain

case class Leg private(path: Path, distance: Int) {
  
  assert (distance > 0, "distance must be greater than zero: "+ toString)
  
  def origin = path.origin
  def destination = path.destination
  def isLinkableTo(leg: Leg) = destination == leg.origin
  def startsAt(origin: Char) = this.origin == origin
  def endsAt(destination: Char) = this.destination == destination
  override def toString = path + distance.toString
  
}

object Leg {
	
  private val LegRE = """^([A-Z]{2})(\d+)$""".r
  
  implicit def apply(legStr: String): Leg = {
    legStr match {
      case LegRE(path, distance) => Leg(path, distance.toInt)
      case _ => throw new IllegalArgumentException(legStr +" does not match "+ LegRE)
    }
  }
  
}
