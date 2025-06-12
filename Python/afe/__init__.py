# -*- coding: utf-8 -*-

from . import structs as Structs
from . import exceptions as Exceptions
from . import algorithms as Algorithms

# This file is part of the afe package.
# It is a collection of classes that represent various data structures, exceptions, and algorithms.

__all__: list[str] = [
    "Structs",
    "Exceptions",
    "Algorithms",
]

__version__: str = "v0.0.3"
__license__: str = "MIT"

# This is the main module of the afe package.
# It is designed to be imported as a package, but can also be imported as components.
