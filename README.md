# AlgorithmsForEveryone

A comprehensive collection of common algorithms and data structures implemented in Java.

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.mihaistreames/afe.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.mihaistreames/afe)
[![Sonatype Central](https://maven-badges.sml.io/sonatype-central/io.github.mihaistreames/afe/badge.svg)](https://central.sonatype.com/artifact/io.github.mihaistreames/afe)

## Installation

### Maven

```xml
<dependency>
    <groupId>io.github.mihaistreames</groupId>
    <artifactId>afe</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Quick Start

```java
import io.github.mihaistreames.afe.algorithms.Algorithms;

import java.util.Arrays;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);

        // Sort using different algorithms
        Algorithms.quickSort(numbers);
        System.out.println("QuickSort: " + numbers);

        // Search in sorted data
        int index = Algorithms.binarySearch(numbers, 25);
        System.out.println("Found 25 at index: " + index);
    }
}
```

## Available Algorithms

### Sorting Algorithms

- **QuickSort** - O(n log n) average, O(n²) worst case
- **MergeSort** - O(n log n) guaranteed, stable
- **HeapSort** - O(n log n) guaranteed, in-place
- **InsertionSort** - O(n²) worst case, efficient for small datasets
- **SelectionSort** - O(n²) all cases, minimal swaps
- **ShellSort** - O(n log²n) to O(n^1.5), good for medium datasets
- **BubbleSort** - O(n²) worst case, educational purposes

### Searching Algorithms

- **Binary Search** - O(log n), requires sorted input

## Usage Examples

### Custom Comparator

```java
List<String> words = Arrays.asList("banana", "apple", "cherry");
Algorithms.quickSort(words, String::compareToIgnoreCase);
```

### Array Sorting

```java
Integer[] array = {64, 34, 25, 12, 22, 11, 90};
Algorithms.mergeSort(array);
```

### Individual Algorithm Classes

```java
import io.github.mihaistreames.afe.algorithms.sorting.QuickSort;
import io.github.mihaistreames.afe.algorithms.searching.BinarySearch;

// Direct access to algorithm implementations
QuickSort.sort(myList);

int index = BinarySearch.binarySearch(sortedList, target);
```

## Requirements

- Java 21 or higher
- No external dependencies (only JetBrains annotations for development)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.