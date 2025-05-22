from Blueprints.Data_Structures import StaticGraph

class Graph(StaticGraph):
    def __init__(self, V, vertices):
        super().__init__(V)
        self.vertices = vertices

    def ranking(self, d, v):
        visited = [False] * self.V
        visited[v] = True

        queue = [v]

        while d:
            next_queue = []

            for vertex in queue:
                for neighbor, weight in enumerate(self.adj[vertex]):
                    if weight and not visited[neighbor]:
                        visited[neighbor] = True
                        next_queue.append(neighbor)

            queue = next_queue
            d -= 1

        ranking = []

        for vertex in range(self.V):
            if visited[vertex]:
                ranking.append(self.vertices[vertex])

        print(ranking)


if __name__ == "__main__":
    vertices = [
        ("A", 100, [1, 2, 3]),
        ("B", 200, [0, 3, 4]),
        ("C", 300, [4, 7]),
        ("D", 400, [5, 6]),
        ("E", 500, [8, 10]),
        ("F", 600, [7, 9]),
        ("G", 700, [0, 9]),
        ("H", 800, []),
        ("I", 900, [1]),
        ("J", 1000, []),
        ("K", 1100, [])
    ]

    G_adj = [
        #A, B, C, D, E, F, G, H, I, J, K
        [0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0],
        [1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0],
        [0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
    ]

    G = Graph(11, vertices)
    G.adj = G_adj

    G.ranking(2, 0)
    G.ranking(2, 1)
    G.ranking(2, 2)