package tsp.domain

case class Path private (origin: Char, destination: Char) {
	
  assert (origin != destination, "origin and destination cannot be the same: "+ toString)
  
  override def toString = origin.toString + destination
  
}

object Path {
	
  val PathRE = "^([A-Z]{1})([A-Z]{1})$".r
  
  implicit def apply(pathStr: String): Path = pathStr match {
      case PathRE(origin, destination) 
      	=> Path(origin(0), destination(0))
      case _ 
      	=> throw new IllegalArgumentException(pathStr +" does not match "+ PathRE)
  }
  
}
