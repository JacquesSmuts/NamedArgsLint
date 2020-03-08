package com.jacquessmuts.namedargs

import org.junit.Test
import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import io.flatcircle.preferenceslint.NamedArgumentsDetector
import org.junit.Ignore
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Tests for the [NamedArgumentsDetector] custom lint check.
 */
@RunWith(JUnit4::class)
class NamedArgumentsDetectorTest : LintDetectorTest() {

    override fun getIssues(): MutableList<Issue> =
        mutableListOf(NamedArgumentsDetector.ISSUE)

    override fun getDetector(): Detector = NamedArgumentsDetector()

    @Test
    fun testKotlin_expectPass() {
        lint()
            .allowMissingSdk()
            .files(
                kotlin(
                    """
package com.jacquessmuts.namedargs

class AgeThing(age: Int)
data class User(val name: String, val surname: String, val age: AgeThing, val fingers: Int)

fun test() {
    val namedUser = User(name = "Namey", surname = "McNameface", age = AgeThing(30), fingers = 10)
}
        """
                )
            )
            .run()
            .expectClean()
    }

    @Test
    fun testKotlin_expectFail() {
        lint()
            .allowMissingSdk()
            .files(
                kotlin(
                    """
package com.jacquessmuts.namedargs

class AgeThing(age: Int)
data class User(val name: String, val surname: String, val age: AgeThing)

fun test() {
    val namedUser = User("Namey", surname = "McNameface", age = AgeThing(30))
}
        """
                )
            )
            .run()
            .expect(
                """
src/com/jacquessmuts/namedargs/AgeThing.kt:8: Error: Constructer calls should use named elements [NamedArgumentsDetector]
    val namedUser = User("Namey", surname = "McNameface", age = AgeThing(30))
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1 errors, 0 warnings
            """
            )
    }
}