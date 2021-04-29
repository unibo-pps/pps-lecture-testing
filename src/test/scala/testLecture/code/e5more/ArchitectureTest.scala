package testLecture.code.e5more

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition._
import org.junit.jupiter.api.Test

class MyArchitectureTest {
  @Test
  def someArchitecturalRule() {
    val importedClasses: JavaClasses = new ClassFileImporter().importPackages("testLab")

    val rule: ArchRule  = classes().should().accessClassesThat().resideInAPackage("testLab")

    rule.check(importedClasses)
  }
}