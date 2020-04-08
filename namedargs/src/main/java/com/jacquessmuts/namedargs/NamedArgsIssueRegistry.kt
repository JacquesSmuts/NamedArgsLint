package com.jacquessmuts.namedargs

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class NamedArgsIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(NamedArgumentsDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API
}