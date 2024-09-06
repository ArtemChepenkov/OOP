javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.chepenkov

javac src/main/java/ru/nsu/chepenkov/HeapSort.java -d ./build

java -cp ./build ru.nsu.chepenkov.HeapSort