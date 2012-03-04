package tsp

import tsp.solver._

object SmallTSP {

  private val DefaultGraph = Array("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
  
  def main(args: Array[String]) {
    
    val start = System.currentTimeMillis
    
    println("Small TSP\n")
    
    if (args.isEmpty) {
      printUsage
      println("\nNo user graph specified, using default..\n");
      solve(DefaultGraph)
    } else {
      solve(args)
    }
    
    val end = System.currentTimeMillis
    println
    println("time: " + (end - start) / 1000d +" msecs")
  }
  
  private def solve(graph: Array[String]) {
    println("Graph: "+ graph.mkString(" "))
    println
    try {
      val solver = TourSolver(graph: _*)
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
    println(e);
    println("Please verify that your usage is correct.\n");
    printUsage();
  }
  
  private def printUsage() {
    println("Usage:");
    println("  scala tsp.SmallTSP <edge1> <edge2> ..<edgeN>");
    println("Example:");
    println("  scala tsp.SmallTSP " + DefaultGraph.mkString(" "));
  }
  
}
