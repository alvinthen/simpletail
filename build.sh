#!/bin/sh
javac -d . -cp libs/commons-cli-1.2.jar src/com/ythen/simpletail/SimpleTail.java
jar cfm SimpleTail.jar manifest com/ythen/simpletail/SimpleTail.class libs/commons-cli-1.2.jar
