from Blueprints.Data_Structures import StaticGraph
from Blueprints.Algorithms.Graph import roy_warshall

if __name__ == "__main__":
    G_adj = [
        [0, 1, 0, 0, 1],
        [0, 0, 1, 0, 1],
        [0, 0, 0, 1, 0],
        [0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0,]
    ]

    G = StaticGraph(5)
    G.adj = G_adj

    print(G.adj)
    print(roy_warshall(G))  # which is the transitive closure of G