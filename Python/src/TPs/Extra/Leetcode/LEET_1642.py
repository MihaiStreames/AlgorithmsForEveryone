class Solution:
    def min_heap_push(self, heap, value):
        heap.append(value)
        self.swim_up(heap, len(heap) - 1)

    def min_heap_pop(self, heap):
        heap[0], heap[-1] = heap[-1], heap[0]
        result = heap.pop()
        self.swim_down(heap, 0)
        return result

    def swim_up(self, heap, index):
        while index > 0:
            parent = (index - 1) // 2
            if heap[parent] <= heap[index]:
                break

            heap[parent], heap[index] = heap[index], heap[parent]
            index = parent

    def swim_down(self, heap, index):
        while index * 2 + 1 < len(heap):
            left = index * 2 + 1
            right = index * 2 + 2
            smallest = index

            if left < len(heap) and heap[left] < heap[smallest]:
                smallest = left

            if right < len(heap) and heap[right] < heap[smallest]:
                smallest = right

            if smallest != index:
                heap[index], heap[smallest] = heap[smallest], heap[index]
                index = smallest
            else:
                break

    def furthestBuilding(self, heights, bricks, ladders):
        heap = []

        for i in range(len(heights) - 1):
            if heights[i + 1] <= heights[i]:
                continue

            diff = heights[i + 1] - heights[i]

            if diff > 0:
                self.min_heap_push(heap, diff)

                if len(heap) > ladders:
                    bricks -= self.min_heap_pop(heap)
                if bricks < 0:
                    return i

        return len(heights) - 1

"""
import heapq

class SolutionNoCustom():
    def furthestBuilding(self, heights, bricks, ladders):
        heap = []

        for i in range(len(heights) - 1):
            if heights[i + 1] <= heights[i]:
                continue

            diff = heights[i + 1] - heights[i]

            if diff > 0:
                heapq.heappush(heap, diff)

                if len(heap) > ladders:
                    bricks -= heapq.heappop(heap)
                if bricks < 0:
                    return i

        return len(heights) - 1
"""