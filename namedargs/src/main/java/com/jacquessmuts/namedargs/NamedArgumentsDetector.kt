package io.flatcircle.preferenceslint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.*


class NamedArgumentsDetector : Detector(), SourceCodeScanner {

    //https://github.com/chrimaeon/lint-nullify/blob/master/checks/src/main/java/com/cmgapps/lint/NullifyAnnotationDetector.java

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return NamedArgumentsHandler(context)
    }

    internal class NamedArgumentsHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitCallExpression(node: UCallExpression) {
            val arguments = node.valueArguments
            val actualText = node.getContainingDeclaration()?.text

            if (actualText == null || arguments.size <= 1)
                return

            arguments.forEach { argument ->
                val startLocation = actualText.indexOf(argument.evaluate().toString(), 0, true)

                if (startLocation < 0)
                    return@forEach

                if (!actualText.substring((startLocation-3)..startLocation).contains('=') ) {
                    context.report(
                        issue = ISSUE,
                        scope = node,
                        location = context.getLocation(node),
                        message = "Constructer calls should use named elements"
                    )
                }

            }
        }

    }

    companion object {
        val ISSUE = Issue.create(
            id = "NamedArgumentsDetector",
            briefDescription = "Enforces usage of named arguments",
            explanation = "Any constructor with more than 2 arguments need to use named arguments",
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            implementation = Implementation(
                NamedArgumentsDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

}
