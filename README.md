# Writing-Robot
Uses javaFX
Svg.java creates converted.txt
converted.txt contains n cubic bezeier curves represented 8n space seperated real numbers.
There is no seperator for each bezier curve. The file consists only of space-seperated real numbers.
The 8 numbers for the bezier curve are x,y pairs of 4 points: start point, control point 1, control point 2, end point

Main.java gives a fixed amount of time for plotting all points on 1 bezier. Treating the bezeir curve as a paramatterized function of t (goes from 0 to 1 incrementally), points are retreived as output, with t given as input.<br>
Using the point retreived the angles of both pivots are calculated and set, moving the pen to that point. If the length of the two rotating sticks are l1 and l2, the pen is capable f reaching points between the concentric circles of radius l1+l2 and |l1-l2|
