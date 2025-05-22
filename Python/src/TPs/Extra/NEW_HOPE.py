class NouvelEspoir:
    def __init__(self, G, k):
        self.G = G
        self.k = k
        self.coloring = [-1] * G.n  # -1 indicates that the planet is yet unassigned

    def save_galaxy(self):
        if self.color_graph(0):
            return self.coloring
        else:
            return "No solution found, galaxy not saved :("

    def color_graph(self, node):
        if node == self.G.n:
            return True  # All nodes colored successfully

        for color in range(1, self.k + 1):
            if self.is_valid_color(node, color):
                self.coloring[node] = color

                if self.color_graph(node + 1):
                    return True
                self.coloring[node] = -1  # Backtrack

        return False

    def is_valid_color(self, node, color):
        for neighbor in self.G.neighbors[node].edges:
            if self.coloring[neighbor.dest] == color:
                return False
        return True