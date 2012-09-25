package com.rowehl.mlia

object DecisionTree {
  /**
   * Returns a summary map giving count for each string.
   *
   * @param l list of strings to count
   * @returns a map from string token to count of occurances in l
   */
  def labelCounts(l:List[String]):Map[String,Int] = {
    l.foldLeft(Map[String,Int]()){ (i:Map[String,Int], s:String) =>
      i + (s -> (i.getOrElse(s, 0) + 1))
    }
  }

  def log2(x: Double) = scala.math.log(x)/scala.math.log(2)

  /**
   *
   */
  def shannonEntropy(dataSet:List[String]):Double = {
    val counts = labelCounts(dataSet)
    counts.foldLeft(0.0){ (d,c) =>
      val prob = c._2.toDouble / dataSet.size.toDouble
      d - (prob * log2(prob))
    }
  }
}
