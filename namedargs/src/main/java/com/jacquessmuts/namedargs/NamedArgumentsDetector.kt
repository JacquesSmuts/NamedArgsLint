package com.jacquessmuts.namedargs

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.uast.*

class NamedArgumentsDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return NamedArgumentsHandler(context)
    }

    internal class NamedArgumentsHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitCallExpression(node: UCallExpression) {

            val arguments = (node.sourcePsi as? KtCallExpression)?.valueArguments
            if (arguments == null || arguments.size <= 2)
                return

            arguments.forEach { argument ->
                if (!argument.isNamed()) {
                    context.report(
                        issue = ISSUE,
                        scope = node as UElement,
                        location = context.getLocation(node as UElement),
                        message = "Constructer and function calls should use named elements"
                    )
                }
            }
        }

    }

    companion object {
        val ISSUE = Issue.create(
            id = "NamedArgumentsDetector",
            briefDescription = "Enforces usage of named arguments",
            explanation = "Any constructor with 3 or more arguments need to use named arguments",
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.ERROR,
            implementation = Implementation(
                NamedArgumentsDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

}
