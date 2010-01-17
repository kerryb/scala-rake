import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class MessageSpec extends Spec with ShouldMatchers {

  describe("The message") {
    val message = new Message

    it("has a value of 'Hello world!'") {
      message.value should equal("Hello world!")
    }
  }
}
