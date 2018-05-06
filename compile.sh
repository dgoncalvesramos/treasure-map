#!/bin/bash

JC=javac
FLAG=-d
PROGRAM_NAME=Main.java

mkdir bin/
cd src
${JC} ${FLAG} ../bin $PROGRAM_NAME

if [ $? -eq 0 ]
then
  echo "Compile successfully !"
fi
