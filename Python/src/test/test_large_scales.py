from typing import List

import pytest

from AlgorithmsForEveryone.data_structures.heaps.max_heap import MaxHeap
from utils.generators import gen_unique_list, gen_linked_list, gen_queue, gen_stack

TEST_SIZES = [10, 100, 1000, 10000, 100000, 1000000]


@pytest.mark.parametrize("size", TEST_SIZES)
def test_max_heap_large(size: int):
    """Test MaxHeap with large random lists."""
    data: List[int] = gen_unique_list(size)
    # Create heap from list and then insert one extra element
    mh = MaxHeap(data)
    mh.insert(max(data) + 1)
    # Remove all elements; result should be in descending order.
    result = []
    while True:
        val = mh.delete()
        if val is None:
            break
        result.append(val)
    # Check that result is sorted in descending order and length is as expected.
    assert result == sorted(result, reverse=True)
    assert len(result) == size + 1


@pytest.mark.parametrize("size", TEST_SIZES)
def test_linked_list_large(size: int):
    """Test UnorderedLinkedList with large random data."""
    ll = gen_linked_list(size)
    # Verify the list contains the correct number of items.
    count = sum(1 for _ in ll)
    assert count == size
    # Remove the first element and ensure it cannot be found.
    if size > 0:
        first = next(iter(ll))
        ll.remove(first)
        assert ll.search(first) is None


@pytest.mark.parametrize("size", TEST_SIZES)
def test_queue_large(size: int):
    """Test Queue with large random data."""
    q = gen_queue(size)
    result = []
    while not q.is_empty():
        result.append(q.dequeue())
    # Since Queue is FIFO, the number of dequeued items should match.
    assert len(result) == size


@pytest.mark.parametrize("size", TEST_SIZES)
def test_stack_large(size: int):
    """Test Stack with large random data."""
    s = gen_stack(size)
    result = []
    while s.size() > 0:
        result.append(s.pop())
    # For Stack (LIFO), the number of popped items should equal the inserted count.
    assert len(result) == size
