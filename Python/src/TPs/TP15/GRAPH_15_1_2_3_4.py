from Blueprints.Data_Structures import StaticGraph, Queue
from Blueprints.Algorithms.Graph import dfs_static
from Blueprints.Algorithms.Pathfinding import bfs_static

def shortest_path(G, start, dest):
    dist = [float('inf') for _ in range(G.V)]
    dist[start] = 0

    parent = [-1 for _ in range(G.V)]

    q = Queue()
    q.enqueue(start)

    while q:
        curr = q.dequeue()

        for neighbor, weight in enumerate(G.adj[curr]):
            if weight and dist[neighbor] > dist[curr] + weight:
                dist[neighbor] = dist[curr] + weight
                parent[neighbor] = curr
                q.enqueue(neighbor)

    path = []
    curr = dest

    while curr != -1:
        path.insert(0, curr)
        curr = parent[curr]

    return dist[dest], path

def convert(list):
    return [chr(vertex + ord('A')) for vertex in list]

if __name__ == "__main__":
    G_adj = [
        #A, B, C, D, E, F, G, H, I, J, K
        [0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]

    G = StaticGraph(11)
    G.adj = G_adj

    print(convert(dfs_static(G, 0)))
    print(convert(bfs_static(G, 0)))
    print(convert(bfs_static(G, 8)))

    # convert the vertices to letters
    length, path = shortest_path(G, 3, 10)
    print(length, convert(path))