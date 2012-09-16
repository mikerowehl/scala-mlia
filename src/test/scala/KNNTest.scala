import org.scalatest._
import com.rowehl.mlia.KNN._
import breeze.linalg._

class KNNTest extends FunSuite {
  // Test data and labels. a values clustered around 0,0 and b values
  // clustered around 1,1
  val d = DenseMatrix((0.0,0.1),(1.1,1.0),(1.0,1.0),(0.0,0.0))
  val l = Array("a", "b", "b", "a")

  test("Classify a sample") {
    assert("b" == classify0(List(1.0, 1.2), d, l, 2))
  }

  test("Reverse data classifies the same") {
    val reverseMatrix = DenseMatrix.tabulate(d.rows, d.cols)((x,y) => d((d.rows-1)-x,y))
    assert("b" == classify0(List(1.0, 1.2), reverseMatrix, l.reverse, 2))
  }
}
