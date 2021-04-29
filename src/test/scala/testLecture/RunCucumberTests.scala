package testLecture

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(features = Array("classpath:features/MyFeature.feature"),
  tags = Array("not @Wip"), glue = Array("classpath:testLecture.code.e4bdd.steps"),
  plugin = Array("pretty", "html:target/cucumber/html"))
class RunCucumberTests
