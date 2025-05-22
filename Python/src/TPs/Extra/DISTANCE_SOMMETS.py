class Arete:
    def __init__(self, poids, destination):
        self.poids = poids
        self.destination = destination

class Sommet:
    def __init__(self, idx, areteParent=None):
        self.idx = idx
        self.areteParent = areteParent
        self.aretesEnfants = []

def distances_sommets(racine, n):
    M = [[float('inf')] * n for _ in range(n)]

    def dfs(noeud, parent, poids):
        for arete in noeud.aretesEnfants:
            enfant = arete.destination

            if enfant == parent:
                continue
                
            poids_enfant = poids + arete.poids
            M[noeud.idx][enfant.idx] = poids_enfant
            M[enfant.idx][noeud.idx] = poids_enfant
            dfs(enfant, noeud, poids_enfant)

    # Start DFS from the root
    dfs(racine, None, 0)

    # Set distance from each node to itself as 0
    for i in range(n):
        M[i][i] = 0

    return M