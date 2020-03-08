package com.jacquessmuts.namedargs;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.flatcircle.preferenceslint.NamedArgumentsDetector;

public final class NamedArgsIssueRegistry extends IssueRegistry {
    @Override public List<Issue> getIssues() {
        return Collections.singletonList(NamedArgumentsDetector.Companion.getISSUE());
    }

    @Override public int getApi() {
        return ApiKt.CURRENT_API;
    }
}
