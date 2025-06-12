# -*- coding: utf-8 -*-

from .StructException import (
    StructException,  # Base class for all exceptions in the afe package
    BaseUnionFindException, BaseGraphException, BaseNodeException,
    BaseStackException, BaseQueueException, BaseRBTException,
)


# This file is part of the exception module.
# It is a collection of classes that represent various exceptions relative to algorithms and data structures.

__all__: list[str] = [
    "StructException",
    "BaseUnionFindException",
    "BaseGraphException",
    "BaseNodeException",
    "BaseStackException",
    "BaseQueueException",
    "BaseRBTException",
]

# This module is not designed to be imported as a standalone module, rather it is meant to be imported as part of the afe package.
