package testLecture

import org.junit.platform.suite.api.{ConfigurationParameter, SelectClasspathResource, Suite}
import org.junit.runner.RunWith
import io.cucumber.junit.platform.engine.Constants.{FILTER_TAGS_PROPERTY_NAME, GLUE_PROPERTY_NAME, PLUGIN_PROPERTY_NAME}

@Suite
@SelectClasspathResource("features/MyFeature.feature")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, "not @Wip")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "classpath:testLecture.code.e4bdd.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty; html:target/cucumber/html")
class RunCucumberTests
