# Algorithms SPE 5  Swarm Particle Optimization

This exercise calculates functions with the help of a swarm of "bees". We use the SPO (swarm particle optimization) global best algorithm to do our calculations. When calculating our new vectors, the movement of the bee will have a direction parallel to new velocity but at the same time have a direction towards its personal best and the global best. See picture below.

![alt text](https://github.com/Schultzz/AlgoSPOSPE/blob/master/Velocity.png?raw=true "SPO velocity")

### Two dimension

Function to be solved: z = x * exp(– x*x – y*y)

Search area: –2 < x < 2 and –2 < y < 2

#### Compared to earth:
Magic plz

#### Calculated maximum distance:

| Swarm size | Iterations | Maximum distance |
 ----------------- | -------------- | -------------
| 5| 80| 1.005909243684975 |
| 10| 100| 0.24908599111756036|
| 15| 120| 0.011690930797432981|
| 20| 140| 0.02151644479401748|
| 25| 160| 0.011957369888485433|
| 30| 180| 0.022372102242989265|
| 35| 200| 4.366737705744105E-5|
| 40| 220| 0.18563672276317492|

### Four dimension

Function to be solved: z = 2 * x * exp(– x*x – y*y – (u–1)*(u–1) – w*w)

Search area:–2 < x < 2 and –2 < y < 2 and –2 < u < 2 and –2 < w < 2

Max value of the function: 0.15588325260118763
