#!/usr/bin/env bash
function test() {
    ./gradlew test
}

function sync() {
    git stash push -m "Rebasing"
    git pull --rebase
    git rebase -i --autosquash origin/master
    git push
    git stash pop
}

function commit() {
    sync
    git add .
    git commit
    git tag -f lastRed
}

function revert() {
    git reset --hard
    git clean -f
}

test && revert || commit
