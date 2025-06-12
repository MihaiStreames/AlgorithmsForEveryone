# AlgorithmsForEveryone - Python Implementation

This directory contains the Python implementation of the `afe` library.

## Requirements

- Python 3.6 or higher.

## Installation

_This package is not yet available on PyPI._

## Quick Start

### Sorting

```python
from afe.algorithms.sorting import quick_sort
# Or import other algorithms like merge_sort, heap_sort, etc.

numbers = [64, 34, 25, 12, 22, 11, 90]

# Sort the list in-place
quick_sort.sort(numbers)

print(f"Sorted list: {numbers}")
```

### Data Structures

```python
from afe.structs.graphs import Graph
from afe.algorithms.graphs import BreadthFirstSearch

# Create a graph
g = Graph(5)
g.add_edge(0, 1)
g.add_edge(0, 2)
g.add_edge(1, 3)
g.add_edge(2, 4)

# Find shortest path from 0 to 4
bfs = BreadthFirstSearch(g, 0)
if bfs.has_path_to(4):
    print(f"Path to 4: {list(bfs.path_to(4))}")
print(f"Distance to 4: {bfs.dist_to(4)}")
```
