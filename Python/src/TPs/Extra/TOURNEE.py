class Graph:
    def __init__(self, n: int):
        ...
    def voisins_de(self, v: int) -> list[int]:
        # retourne la liste des indices des sommets reliés à v
        ...

    def nombre_de_fans_dans(self, v: int) -> int:
        # retourne le nombre de fans dans v
        ...

    def cout_concert_dans(self, v: int) -> int:
        # retourne le cout de l’organisation d’un concert dans v
        ...

    def cout_trajet_entre(self, v: int, w: int) -> int:
        # retourne le cout du trajet entre v et w
        ...

    def __len__(self) -> int:
        # retourne le nombre de sommets du graphe
        ...

class Tournee:
    def __init__(self, G, depart, longueur_tournee, budget):
        self.G = G
        self.depart = depart
        self.longueur_tournee = longueur_tournee
        self.budget = budget
        self.best_fans = 0
        self.best_itineraire = []
        self.best_cost = 0

    def dfs(self, v, days, budget, fans, path):
        if days == self.longueur_tournee:
            if v == self.depart and fans > self.best_fans:
                self.best_fans = fans
                self.best_itineraire = path[:]
                self.best_cost = self.budget - budget
            return

        for w in self.G.voisins_de(v):
            travel_cost = self.G.cout_trajet_entre(v, w)
            concert_cost = self.G.cout_concert_dans(w)
            total_cost = travel_cost + (concert_cost if w not in path else 0)

            if budget >= total_cost and (w not in path or w == self.depart):
                path.append(w)
                self.dfs(w, days + 1, budget - total_cost, fans + (self.G.nombre_de_fans_dans(w) if w not in path else 0), path)
                path.pop()

    def trouver_itineraire(self):
        self.dfs(self.depart, 0, self.budget, 0, [self.depart])
        return self.best_itineraire, self.best_fans, self.best_cost, self.longueur_tournee