simpletail
==========
To build SimpleTail, run build.sh.

Example usage
=============
Display last 10 lines of example.txt
```
java -jar SimpleTail.jar example.txt
```
Display last 5 lines of example.txt
```
java -jar SimpleTail.jar -n 5 example.txt
```
Display lines after the 5th byte (inclusive) of example.txt
```
java -jar SimpleTail.jar -c +5 example.txt
```
Display usage
```
java -jar SimpleTail.jar --help
```
