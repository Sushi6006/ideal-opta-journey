************************************************************************
file with basedata            : md372_.bas
initial value random generator: 177413554
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  158
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       24       12       24
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           7  13  16
   3        3          3           5   6  12
   4        3          1          16
   5        3          3          11  14  18
   6        3          3           8   9  11
   7        3          1          10
   8        3          1          15
   9        3          3          10  16  18
  10        3          3          14  15  19
  11        3          1          21
  12        3          1          13
  13        3          3          14  15  19
  14        3          2          17  20
  15        3          2          17  20
  16        3          1          19
  17        3          1          21
  18        3          2          20  21
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     2       0    8    4    7
         2     6       0    7    3    6
         3     6       0    6    4    6
  3      1     4       0    8    9    9
         2     6       5    0    8    6
         3    10       5    0    5    6
  4      1     5       0    9    8    8
         2     6       4    0    6    5
         3     9       0    8    6    2
  5      1     5       9    0    8    2
         2     7       6    0    7    2
         3    10       5    0    6    2
  6      1     2       0    8    7    8
         2     4       0    5    1    7
         3     4       3    0    5    8
  7      1     2       5    0    8    3
         2     4       0    7    5    1
         3     4       3    0    2    1
  8      1     1       7    0    8    8
         2     1       0    7    7    8
         3     4       7    0    6    6
  9      1     6       0    6    8    9
         2     7       2    0    4    7
         3     8       1    0    4    3
 10      1     3       0   10    7    7
         2     7       0    6    7    6
         3    10       9    0    4    6
 11      1     2       0    4    8    6
         2     9       0    1    2    6
         3     9       5    0    6    5
 12      1     5       0    5    7    3
         2     5       5    0    9    5
         3    10       5    0    6    3
 13      1     3       0    6    9    8
         2     4       5    0    8    6
         3     6       0    6    7    5
 14      1     1       0    4    2    5
         2     1       4    0    2    6
         3     2       0    4    2    4
 15      1     3       0    7    9    8
         2     6       0    3    6    7
         3     9       6    0    3    7
 16      1     3       0    7    7    5
         2     7       0    6    6    5
         3    10       0    6    5    5
 17      1     4       0    9    8   10
         2     6       0    8    8    8
         3    10       0    6    8    8
 18      1     1       3    0    6    7
         2     8       1    0    4    7
         3    10       0    8    3    4
 19      1     5       0    7    9    6
         2     6       0    6    5    5
         3     9       0    6    1    3
 20      1     4       9    0    6    6
         2     9       8    0    4    5
         3    10       6    0    2    3
 21      1     2       7    0    5    8
         2     4       0    4    4    5
         3     8       5    0    3    2
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   23   29  129  124
************************************************************************
