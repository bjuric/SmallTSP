package tsp

import tsp.solver._

object SmallTSP {

  private val DefaultArgs = Array("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
  
  def main(args: Array[String]) {
    
    val start = System.currentTimeMillis
    
    println("Small TSP\n")
    
    if (args.isEmpty) {
      printUsage
      println("\nNo user input arguments specified, using defaults..\n")
      solve(DefaultArgs)
    } else {
      solve(args)
    }
    
    val end = System.currentTimeMillis
    println
    println("time: " + (end - start) / 1000d +" msecs")
  }
  
  private def solve(args: Array[String]) {
    println("Legs: "+ args.mkString(" "))
    println
    try {
      val solver = RouteSolver(args: _*)
      println("Results:")
      for ((question, answer) <- solver.solve) {
        println(question +" = "+ answer)
      }
    } 
    catch {
      case e => handleError(e)
    }
  }
  
  private def handleError(e: Throwable) {
    println(e)
    println("Please verify that your usage is correct.\n")
    printUsage()
  }
  
  private def printUsage() {
    println("Usage:")
    println("  scala tsp.SmallTSP <leg1> <leg2> ..<legN>")
    println("Example:")
    println("  scala tsp.SmallTSP " + DefaultArgs.mkString(" "))
  }
  
}
