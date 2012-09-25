package com.rowehl.mlia

object DecisionTree {
  /**
   * Starting with a map of string counts (m) walk the list of string passed
   * in and add the occurances to a new map, which is the return value.
   *
   * @param m map of strings to the count of their occurances
   * @param l list of strings to add to the counts
   * @returns a new map with the counts updates to reflect the strings in l
   */
  def countElements(m:Map[String,Int], l:List[String]):Map[String,Int] = {
    l.foldLeft(m){ (i:Map[String,Int], s:String) =>
      i + (s -> (i.getOrElse(s, 0) + 1))
    }
  }

  /**
   * Generate a count of each string across a set of lists.
   *
   * @param dataSet a list of lists of strings
   * @returns a map from string to number of occurances across all lists
   */
  def labelCounts(dataSet:List[List[String]]):Map[String,Int] = {
    dataSet.foldLeft(Map[String,Int]()){ (i:Map[String,Int], d:List[String]) =>
      countElements(i, d)
    }
  }

  def log2(x: Double) = scala.math.log(x)/scala.math.log(2)

  /**
   *
   */
  def shannonEntropy(dataSet:List[List[String]]):Double = {
    val counts = labelCounts(dataSet)
    counts.foldLeft(0.0){ (d,c) =>
      val prob = c._2.toDouble / dataSet.size.toDouble
      d - (prob * log2(prob))
    }
  }
}
