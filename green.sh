#!/usr/bin/env bash
function test() {
    ./gradlew test
}

function commit() {
    git add .
    git commit --squash=lastRed
}

function revert() {
    git reset --hard
    git clean -f
}

test && commit || revert