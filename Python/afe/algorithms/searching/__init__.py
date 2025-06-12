# -*- coding: utf-8 -*-

from .search import Search
from typing import Callable


# This file is part of the Algorithms module.
# It is a collection of classes that represent various algorithms, including searching algorithms like linear and binary search.

linear_search: Callable[[list, object], int] = Search.linearSearch
binary_search: Callable[[list, object], int] = Search.binarySearch
binary_search_recursive: Callable[[list, object], int] = Search.binarySearchRecursive

__all__: list[str] = [
    "linear_search",
    "binary_search",
    "binary_search_recursive"
]

# This module is designed to be imported as a standalone module, but can also be imported as part of the afe package.
