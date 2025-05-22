from Blueprints.Data_Structures import Queue

def contains(tree, value):
    q = Queue()
    q.enqueue(tree)

    def level_search(queue):
        if queue.is_empty():
            return None

        node = queue.dequeue()

        if node is not None:
            if node.get_root_val() == value:
                return node

            queue.enqueue(node.get_left_child())
            queue.enqueue(node.get_right_child())

        return level_search(queue)
    
    return level_search(q)