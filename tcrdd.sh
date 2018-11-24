#!/usr/bin/env bash
BRANCH=origin/master

function runTest() {
    ./gradlew test
}

function shouldBeRed(){
    [[ ! -z `git diff HEAD | grep "^\+.*@Test"` ]]
}

function shouldBecomeGreen(){
    [[ ! -z `git diff ${BRANCH} HEAD` ]]
}

function commit() {
    git add . && \
    if shouldBeRed; then
        git commit
    elif shouldBecomeGreen; then
        git commit --amend --no-edit
    else
        git commit --allow-empty-message -m ""
    fi
}

function revert() {
    git reset --hard
    git clean -f
}

function sync() {
    git rebase ${BRANCH} \
    && runTest \
    && git push
}

function isErrorMessageOK() {
    read -r -p "Is the error message expressive? [Y/n] " answer
    [[ -z ${answer} || ${answer} == "Y" || ${answer} == "y" ]]
}

KNOWN_AS_GREEN=false

while [[ $# -gt 0 ]]
do
    key="$1"

    case ${key} in
        -g|--green)
            KNOWN_AS_GREEN=true
            shift
            ;;
        *)
            shift
            ;;
    esac
done

if (! ${KNOWN_AS_GREEN}) && shouldBeRed
then
    runTest && revert || (isErrorMessageOK && commit)
else
    runTest && (commit && sync) || revert
fi