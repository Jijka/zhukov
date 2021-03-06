import minitest.SimpleTestSuite
import zhukov.{Default, Marshaller, Unmarshaller}
import zhukov.derivation._
import zhukov.Default.auto._

object DefaultTest extends SimpleTestSuite {

  case class Foo(x: Int, y: Int = 10)
  case class FooOpt(x: Option[Int], y: Int = 10)

  implicit val fooUnmarshaller = unmarshaller[Foo]
  implicit val fooOptMarshaller = marshaller[FooOpt]

  test("Default") {
    val bytes = Marshaller[FooOpt].write(FooOpt(None))
    val result = Unmarshaller[Foo].read(bytes)
    assert(result == Foo(0, 10))
  }
}
