package testLecture

import java.io.PrintWriter

import org.junit.platform.engine.discovery.DiscoverySelectors.{selectClass, selectPackage}
import org.junit.platform.launcher.TagFilter
import org.junit.platform.launcher.core.{LauncherDiscoveryRequestBuilder, LauncherFactory}
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import testLecture.code.e0junit.BTreesTest

/**
  * Example to show how JUnit Platform may be used to run tests, programmatically.
  */
class SomeJUnit5Runner {
  val listener = new SummaryGeneratingListener()

  def runOne(): Unit = {
    val request = LauncherDiscoveryRequestBuilder.request
      .selectors(selectClass(classOf[BTreesTest]))
      .selectors(selectPackage("testLecture"))
      .filters(TagFilter.excludeTags("slow")).build
    val launcher = LauncherFactory.create
    val testPlan = launcher.discover(request)
    println(testPlan.getRoots)
    import scala.collection.JavaConverters._
    for(r <- testPlan.getRoots.iterator().asScala;
        d <- testPlan.getDescendants(r).asScala if d.isTest) {
      println(d)
    }
    launcher.registerTestExecutionListeners(listener)
    launcher.execute(request)
  }
}

object SomeJUnit5Runner extends App {
  val runner = new SomeJUnit5Runner
  runner.runOne()
  val summary = runner.listener.getSummary
  summary.printTo(new PrintWriter(System.out))
}
