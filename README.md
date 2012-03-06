Small TSP (Travelling Salesman Problem)
=======================================

Description
-----------

In the TSP, a tour is a line connecting a set of points.  Every tour has an 
origin and a destination at either the same point or at different points. The 
line segments connecting pairs of points together are called edges. Every 
edge is a path of fixed length from one point to another. Two edges are 
linked if the destination of the path of one edge and the origin of the path 
of the other edge are the same point. A tour can pass through one edge or 
many linked edges. The total length of a tour is equal to the sum of the 
lengths of the edges it passes through. The set of known and valid edges for 
a given set of points is called a graph.

**Given a graph of edges of form**

([A-Z]{2})(\d+)

where

- ([A-Z]{2}) is a path from one point to another
- (\d+) is the length in arbitrary units

**This program solves the following**

1. The length of tour ABC
2. The length of tour AD
3. The length of tour ADC
4. The length of tour AEBCD
5. The length of tour AED
6. All tours from C to C having at most 3 edges
7. All tours from A to C having exactly 4 edges
8. The shortest tour from A to C
9. The shortest tour from B to B
10. All tours from C to C having length less than 30

Usage
-----

    scala tsp.SmallTSP [edge1] [edge2] ..[edgeN]

Sample Usage
------------

**For an input graph of** AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7
  
![Default Graph Image](SmallTSP/raw/master/DefaultGraph.png)
  
**The usage would be**

    scala tsp.SmallTSP AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7

**The result would be**

1. Length of tour ABC = 9
2. Length of tour AD = 5
3. Length of tour ADC = 13
4. Length of tour AEBCD = 22
5. Length of tour AED = No such tour: No such path: ED
6. Tours from C to C having at most 3 edges = CDC, CEBC
7. Tours from A to C having exactly 4 edges = ABCDC, ADEBC, ADCDC
8. Shortest tour from A to C = ABC
9. Shortest tour from B to B = BCEB
10. Tours from C to C having length less than 30 
    = CDEBC, CDC, CDCEBC, CEBC, CEBCDC, CEBCEBC, CEBCEBCEBC

