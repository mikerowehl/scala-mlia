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
   * A conversion of the classify0 algorithm from listing 2.1, a k-Nearest
   * Neighbors classifier. Pass in the sample to classify, the entire set of
   * data and the labels assigned to that data, and the number of neighbors to
   * use in voting.
   *
   * @param sample The new data to classify against existing data
   * @param data The existing points in the dataset, as a matrix
   * @param labels The labels to go along with the data
   * @param k The number of neighbors to include in the voting algorithm
   * @returns The label that should be assigned to the new sample
   */
  def classify0(sample: List[Double], data: DenseMatrix[Double], labels: Array[String], k: Int): String = {
    // TODO: assert numbers of labels == rows of data
    // TODO: assert sample size == columns of data
    // TODO: assert k < rows of data

    // Fill a matrix with the sample and subtract the base data
    val diffMat = fill(sample, data.rows) - data

    // Square the difference matrix
    val sqDiffMat = diffMat :^ DenseMatrix.fill(diffMat.rows, diffMat.cols)(2.0)

    // For each row, calculate the sum of the squares
    val sqDistances = for {
      r <- 0 to sqDiffMat.rows-1
      m = sqDiffMat(r,::)
    } yield m.sum

    // Now take the square root of the sums
    val distances = sqDistances.map(scala.math.pow(_,0.5))

    // Zip the labels into pairs, and sort by the value
    val pairs = labels.zip(distances).sortBy(_._2)

    // Take the first k, and map from label to count
    val votes = pairs.take(k).groupBy(x => x._1).mapValues(_.length)

    // Sort by votes and take the highest count
    val res = votes.toList.sortBy(_._2).last
    res._1
  }
}
