package tsp.domain

class NoSuchPathException (val path: Path) extends Exception("No such path: "+ path)
