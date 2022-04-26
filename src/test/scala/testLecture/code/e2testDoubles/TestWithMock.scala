package testLecture.code.e2testDoubles

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.ArgumentMatchers.*
// import org.scalamock.scalatest.MockFactory
import org.scalatestplus.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
// import org.scalatest.junit.JUnitRunner
// import org.scalatest.{FunSuite, Matchers}
import testLecture.code.Server._

// @RunWith(classOf[JUnitRunner])
/*
class TestWithMock extends AnyFunSuite with MockitoSugar with Matchers: // MockFactory :
  test("Test server with cache"){
    // Mocks/stubs for dependencies
    val cacheMock = mock[Cache[Request,Response]]
    val cacheableStub = stubFunction[Request, Boolean]
    cacheableStub.when( where { (r: Request) => r.body.length < 25 } ).returns(true)
    
    // Wire SUT with stubbed/mocked dependencies
    val server = new ServerWithCache(cacheMock, cacheableStub)
    
    // Arrange
    val request = Request("Some request")
    val expectedResponse = Response(request.body+"'s Response")

    // Mock expectations
    Mockito.when(cacheMock.cached(request)).thenReturn(false)
    Mockito.verify(cacheMock).put(request, any())
    Mockito.verifyNoInteractions(cacheMock)
//    inSequence {
//      (cacheMock.cached _).expects(request).returning(false)
//      (cacheMock.put _).expects(request, *)
//      (cacheMock.cached _).expects(request).returning(true)
//      (cacheMock.get _).expects(request).returning(expectedResponse)
//    }

    // Act + Assert
    server.serve(request) shouldEqual expectedResponse
    server.serve(request) shouldEqual expectedResponse
    server.serve(Request("some long request that should not be cache"))

    cacheableStub.verify(request).twice() // (cf., test spy)
  }
*/