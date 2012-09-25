import org.scalatest._
import com.rowehl.mlia.DecisionTree._
import breeze.linalg._

class DecisionTreeTest extends FunSuite {
  test("Count strings in a list") {
    val res = labelCounts(List("one", "two", "one", "two", "three"))
    assert(res("one") == 2)
    assert(res("two") == 2)
    assert(res("three") == 1)
  }

  def fuzzyCheck(d:Double, e:Double):Boolean = scala.math.abs(e-d) < 0.01

  test("Check Shannon entropy simple values") {
    // One bit of information == 1.0
    val o = List("0", "1")
    val s = shannonEntropy(o)
    assert(fuzzyCheck(s, 1.0))

    // Two bits of information == 2.0
    val t = List("0", "1", "2", "3")
    val s2 = shannonEntropy(t)
    assert(fuzzyCheck(s2, 2.0))

    // One bit of information == 1.0, repeated symbols are meaningless
    val oo = List("0", "1", "0", "1")
    val ss = shannonEntropy(o)
    assert(fuzzyCheck(ss, 1.0))
  }
}
