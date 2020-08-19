************************************************************************
file with basedata            : mm31_.bas
initial value random generator: 1286
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  84
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       17        7       17
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          2           6  11
   3        3          2           5  11
   4        3          3           5   8  11
   5        3          2           7  10
   6        3          1           9
   7        3          1           9
   8        3          1          10
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       0    4    6    6
         2     1      10    0    7    6
         3     6       2    0    4    6
  3      1     5       0    6   10    4
         2     7       5    0   10    3
         3    10       3    0   10    2
  4      1     1       0    4    9    5
         2     2       4    0    8    4
         3     9       1    0    7    2
  5      1     2       9    0    7    7
         2     9       6    0    5    7
         3    10       6    0    4    7
  6      1     2       9    0    5    5
         2     6       9    0    3    5
         3     7       8    0    2    4
  7      1     8       7    0    1    7
         2    10       0    3    1    7
         3    10       4    0    1    6
  8      1     1       8    0    5    9
         2     2       7    0    3    7
         3     6       0    7    1    6
  9      1     2       0    6    3    3
         2     6       0    5    2    2
         3     8       0    4    2    1
 10      1     6       8    0   10    5
         2     7       0    8   10    5
         3     9       0    8   10    4
 11      1     5       0    5    4    6
         2     9       0    3    3    4
         3     9       0    2    4    5
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   22   16   56   53
************************************************************************
