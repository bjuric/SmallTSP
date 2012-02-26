package tsp.domain

class NoSuchRouteException (val offender: Path) extends Exception("Path "+ offender +" does not exist")
