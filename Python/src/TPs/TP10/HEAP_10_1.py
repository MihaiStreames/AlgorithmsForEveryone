from Blueprints.Data_Structures import MinHeap, MaxHeap

v = [8, 1, 9, 4, 7, 6, 10]

min = MinHeap()
max = MaxHeap()

for i in v:
    min.insert(i)
    max.insert(i)

print(min)
print(max)

max.heap_sort(v)
print(v)