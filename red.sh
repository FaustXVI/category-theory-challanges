#!/usr/bin/env bash
function test() {
    ./gradlew test
}

function commit() {
    git add .
    git commit
    git tag -f lastRed
}

function revert() {
    git reset --hard
    git clean -f
}

test && revert || commit