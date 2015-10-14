#!/bin/bash
#export maven_home=/Users/lyt1987/Desktop/mypcsoft/apache-maven-3.2.5/bin
#export PATH=$PATH:$maven_home
showMsg(){
   echo please add param 1-5
   echo 1 : clean install
   echo 2 : test
   echo 3 : clean deploy
}

if [ $# == 0 ]
   then
   showMsg
   exit
fi

if [ $1 == 1 ]
   then
   mvn clean install
   elif [ $1 == 2 ]
   then
   mvn test
   elif [ $1 == 3 ]
   then
   mvn clean deploy
   else
   showMsg
fi