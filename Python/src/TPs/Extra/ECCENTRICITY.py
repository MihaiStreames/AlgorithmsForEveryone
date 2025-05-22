def excentricite(v, G):
    from collections import deque

    # BFS pour calculer la distance de v à tous les autres sommets
    queue = deque([v])
    distances = {v: 0}

    while queue:
        current = queue.popleft()

        for neighbor in G.neighbors[current].edges:
            if neighbor.dest not in distances:
                queue.append(neighbor.dest)
                distances[neighbor.dest] = distances[current] + neighbor.weight  # Utiliser le poids ici, bien que ce soit toujours 1

    # L'excentricité de v est la plus grande valeur dans distances
    return max(distances.values())

def rayon(G):
    excentricites = {i: excentricite(i, G) for i in range(G.n)}
    return min(excentricites.values())