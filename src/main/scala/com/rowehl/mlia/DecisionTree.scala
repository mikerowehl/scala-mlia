package com.rowehl.mlia

import breeze.linalg._

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

  /**
   */
  def checkRow(dataSet:DenseMatrix[Double], col: Int, value: Double):Option[DenseMatrix[Double]] = {
    if (dataSet(0,col) == value) {
      Some(dataSet)
    } else {
      None
    }
  }

  /**
   * Pulls out all the rows where column 'col' is equal to value.
   */
  def splitDataSet(dataSet:DenseMatrix[Double], col: Int, value: Double):DenseMatrix[Double] = {
    val checked = for {
      rowNum <- 0 to dataSet.rows-1
      row = dataSet(rowNum,::)
      c = checkRow(row, col, value)
    } yield c
    val newRows = checked.filter(x => x.nonEmpty).map(_.get)
    newRows.tail.foldLeft(newRows.head){ (e,n) =>
      DenseMatrix.vertcat(e,n)
    }
  }
}
