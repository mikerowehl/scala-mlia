import org.scalatest._
import com.rowehl.mlia.DecisionTree._
import breeze.linalg._

class DecisionTreeTest extends FunSuite {
  test("Count strings in a list") {
    val res = countElements(Map[String,Int](), List("one", "two", "one", "two", "three"))
    assert(res("one") == 2)
    assert(res("two") == 2)
    assert(res("three") == 1)
  }

  test("Count strings in list of lists") {
    val l1 = List("one", "two", "three", "four")
    val l2 = List("two", "four", "five")
    val res = labelCounts(List(l1, l2))
    assert(res("one") == 1)
    assert(res("two") == 2)
    assert(res("three") == 1)
    assert(res("four") == 2)
    assert(res("five") == 1)
  }

  test("Check Shannon entropy simple values") {
    // One bit of information == 1.0
    val o = List(List("0"), List("1"))
    val s = shannonEntropy(o)
    assert(scala.math.abs(1.0-s) < 0.01)
    // Two bits of information == 2.0
    val t = List(List("0"), List("1"), List("2"), List("3"))
    val s2 = shannonEntropy(t)
    assert(scala.math.abs(2.0-s2) < 0.01)
  }
}
