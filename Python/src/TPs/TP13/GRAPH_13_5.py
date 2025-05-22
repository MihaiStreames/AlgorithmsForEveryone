from Blueprints.Data_Structures import StaticGraph

if __name__ == "__main__":
    G_stat = StaticGraph(5)
    G_stat.link(0, 1)
    G_stat.link(0, 4)
    G_stat.link(1, 2)
    G_stat.link(1, 4)
    G_stat.link(2, 3)

    G_dyn = G_stat.to_dynamic()
    G_stat2 = G_dyn.to_static()

    print(G_stat.adj)
    print(G_stat2.adj)

    assert G_stat.adj == G_stat2.adj

    print(G_dyn.neighbors)