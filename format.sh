#!/bin/sh

#find src -name "*.java" -exec java -jar google-java-format-1.18.1-all-deps.jar --replace {} \;
git status | grep -o "src.*.java" | xargs java -jar google-java-format-1.18.1-all-deps.jar --replace {} && git add .