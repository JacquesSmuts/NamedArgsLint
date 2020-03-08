[![CircleCI](https://circleci.com/gh/JacquesSmuts/NamedArgsLint.svg?style=svg)](https://circleci.com/gh/JacquesSmuts/NamedArgsLint) [ ![Download](https://api.bintray.com/packages/jacquessmuts/NamedArgumentsLinter/namedargslinter/images/download.svg) ](https://bintray.com/jacquessmuts/NamedArgumentsLinter/namedargslinter/_latestVersion)

# NamedArgsLint
Adds lint-warnings if a function or constructor is called without named arguments

## Usage
Add this to your dependencies in the modules build.gradle

dependencies {
    lintChecks 'com.jacquessmuts.namedargslinter:<version>'
}

Adding this check will generate a lint warning whenever you pass more than 1 argument to a function or constructor without using named arguments.

## Work in progress

This is a work in progress, as the lint-checks are being done very badly right now. I don't really recommend you use it until version 0.2.0 or so comes out, then I'll remove this section.
