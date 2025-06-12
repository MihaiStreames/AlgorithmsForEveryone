# -*- coding: utf-8 -*-

from .queue import Queue
from .stack import Stack


# This file is part of the RLS (restricted linear structures) submodule.
# It is a collection of classes that represent various models relative to queues and stacks.

__all__: list[str] = [
    "Queue",
    "Stack",
]

# This module is not designed to be imported as a standalone module, rather it is meant to be imported as part of the afe package.
