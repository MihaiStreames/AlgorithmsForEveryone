from Blueprints.Data_Structures.Queue import Queue

def hot_potato(list, n: int) -> str:
    people_queue = Queue()

    for i in list:
        people_queue.enqueue(i)

    while people_queue.size() > 1:
        for i in range(n):
            people_queue.enqueue(people_queue.dequeue())
        people_queue.dequeue()

    return people_queue.dequeue()

print(hot_potato(["Bill", "David", "Susan", "Jane", "Kent", "Brad"], 7))