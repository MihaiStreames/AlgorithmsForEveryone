import random
from typing import List

from AlgorithmsForEveryone.data_structures.linked_list import UnorderedLinkedList
from AlgorithmsForEveryone.data_structures.queue import Queue
from AlgorithmsForEveryone.data_structures.stack import Stack


def gen_unique_list(size: int, lower: int = 1, upper: int = 1000000) -> List[int]:
    """
    Generate a list of unique random integers of a given size in [lower, upper].

    Raises:
        ValueError: If the requested size exceeds the available unique values in [lower, upper].
    """
    population_size = upper - lower + 1
    if size > population_size:
        raise ValueError(f"Cannot generate {size} unique values in range [{lower}, {upper}].")

    # random.sample takes a population and a k, returning k unique elements.
    return random.sample(range(lower, upper + 1), k=size)


def gen_linked_list(size: int, lower: int = 1, upper: int = 1000000) -> UnorderedLinkedList:
    """Generate an UnorderedLinkedList populated with unique random integers."""
    return UnorderedLinkedList(gen_unique_list(size, lower, upper))


def gen_queue(size: int, lower: int = 1, upper: int = 1000000) -> Queue:
    """Generate a Queue populated with unique random integers."""
    q = Queue()
    for num in gen_unique_list(size, lower, upper):
        q.enqueue(num)
    return q


def gen_stack(size: int, lower: int = 1, upper: int = 1000000) -> Stack:
    """Generate a Stack populated with unique random integers."""
    s = Stack()
    for num in gen_unique_list(size, lower, upper):
        s.push(num)
    return s
