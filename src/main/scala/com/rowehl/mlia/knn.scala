package com.rowehl.mlia

import breeze.linalg._

object KNN {
  /**
   * Replicates a sample into a matrix with multiple rows.
   *
   * @param sample The sample as a list of Double values
   * @param rows The number of times to replicate the sample
   * @returns a DenseMatrix with sample.size columns and rows lines
   */
  def fill(sample: List[Double], rows: Int): DenseMatrix[Double] = {
    DenseMatrix.tabulate(rows, sample.size)((x,y) => sample(y))
  }

  /**
   * A conversion of the classify0 algorithm from listing 2.1
   *
   * @param sample The new data to classify against existing data
   * @param data The existing points in the dataset, as a matrix
   * @param labels The labels to go along with the data
   * @param k The number of neighbors to include in the voting algorithm
   * @returns The label that should be assigned to the new sample
   */
  def classify0(sample: List[Double], data: DenseMatrix[Double], labels: Array[String], k: Int): String = {
    val diffMat = fill(sample, data.rows) - data
    val sqDiffMat = diffMat :^ DenseMatrix.fill(diffMat.rows, diffMat.cols)(2.0)
    val sqDistances = for {
      r <- 0 to sqDiffMat.rows-1
      m = sqDiffMat(r,::)
    } yield m.sum
    sqDistances.toString
  }

  def main(args: Array[String]) {
    val d = DenseMatrix((0.0,0.1),(0.0,0.0),(1.0,1.0),(1.0,1.1))
    val l = Array("a", "a", "b", "b")
    println(classify0(List(0.0,0.2), d, l, 2))
  }
}
