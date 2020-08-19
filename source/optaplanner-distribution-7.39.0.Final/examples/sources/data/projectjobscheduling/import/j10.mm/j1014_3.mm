************************************************************************
file with basedata            : mm14_.bas
initial value random generator: 1659369993
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  87
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       18        2       18
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          2           6   7
   3        3          2           5  11
   4        3          2           6   9
   5        3          1           8
   6        3          1          10
   7        3          2           9  11
   8        3          2           9  10
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     2       2    0    8    6
         2     2       0    4    8    6
         3     5       2    0    7    4
  3      1     6       0    2    5    9
         2     7       9    0    5    8
         3     9       9    0    4    5
  4      1     2       7    0    9    5
         2     2       7    0    8    6
         3     8       6    0    3    4
  5      1     3       0    8    6    6
         2     6       8    0    5    5
         3    10       8    0    4    2
  6      1     2       0    5    9    7
         2     8       0    3    6    7
         3    10       5    0    5    6
  7      1     8       0    8    6   10
         2     9       7    0    5    8
         3     9       0    6    3    8
  8      1     3       0    3    8   10
         2     8       2    0    6    4
         3     8       0    2    5    5
  9      1     6       1    0    4    8
         2     8       0    7    4    5
         3    10       0    5    4    2
 10      1     5       6    0    8    5
         2     6       3    0    6    3
         3     9       2    0    4    1
 11      1     1       0    6    5    9
         2     7       0    3    3    7
         3     9       5    0    3    6
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   14    8   55   59
************************************************************************
