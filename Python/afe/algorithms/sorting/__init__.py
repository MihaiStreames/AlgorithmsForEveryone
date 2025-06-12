# -*- coding: utf-8 -*-

from .bubble_sort import BubbleSort
from .heap_sort import HeapSort
from .insertion_sort import InsertionSort
from .merge_sort import MergeSort
from .quick_sort import QuickSort
from .selection_sort import SelectionSort
from .shell_sort import ShellSort

from typing import Callable

# This file is part of the sort submodule.
# It is a collection of classes that represent various exceptions relative to algorithms and data structures.

bubble_sort: Callable[[list, Callable], None] = BubbleSort.sort
heap_sort: Callable[[list, Callable], None] = HeapSort.sort
insertion_sort: Callable[[list, Callable], None] = InsertionSort.sort
merge_sort: Callable[[list, Callable], None] = MergeSort.sort
quick_sort: Callable[[list, Callable], None] = QuickSort.sort
selection_sort: Callable[[list, Callable], None] = SelectionSort.sort
shell_sort: Callable[[list, Callable], None] = ShellSort.sort


__all__: list[str] = [
    "bubble_sort",
    "heap_sort",
    "insertion_sort",
    "merge_sort",
    "quick_sort",
    "selection_sort",
    "shell_sort"
]

# This module is designed to be imported as a standalone module, but can also be imported as part of the afe package.
