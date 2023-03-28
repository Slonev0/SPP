# TP_INTRO
## Comparaisons
Les locks re-entrant sont plus rapide que les read/write 
lock car l'implémentation de ceux-ci sont plus complexe.

Cependant lorsque l'on va venir réduire le nombre d'itération ainsi que rajouter
des delay sur les opérations (sleep). En faissant sa, on va retrouver ce qu'on devrait obtenir, 
c'est a dire que les locks read/write sont plus rapide que les locks re-entrant.

## Mesure du temps
Pour mesurer le temps d'un thread, il faut mesurer au seins de la méthode run()
et pas dans le main(). En effet en fesant dans le main(), la mesure de celui-ci sera
biaisé.
