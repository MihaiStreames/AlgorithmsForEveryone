# AlgorithmsForEveryone - Java Implementation

This directory contains the Java implementation of the `afe` library.

## Requirements

- Java 21 or higher.

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml

<dependency>
    <groupId>com.algorithmsforeveryone</groupId>
    <artifactId>afe</artifactId>
    <version>0.0.3</version>
</dependency>
```

## Quick Start

### Sorting

Directly access the static methods in each sorting class.

```java
import io.github.mihaistreames.afe.algorithms.sorting.QuickSort;

import java.util.Arrays;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);

        // Sort using an algorithm
        QuickSort.sort(numbers);
        System.out.println("Sorted list: " + numbers);
    }
}
```

### Data Structures

Instantiate the data structure classes directly.

```java
import io.github.mihaistreames.afe.algorithms.graphs.BreadthFirstSearch;
import io.github.mihaistreames.afe.structs.graphs.Graph;

public class GraphExample {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        // Find shortest path from 0 to 4
        BreadthFirstSearch bfs = new BreadthFirstSearch(g, 0);
        if (bfs.hasPathTo(4)) {
            System.out.println("Path to 4: " + bfs.pathTo(4));
            System.out.println("Distance to 4: " + bfs.distTo(4));
        }
    }
}
```