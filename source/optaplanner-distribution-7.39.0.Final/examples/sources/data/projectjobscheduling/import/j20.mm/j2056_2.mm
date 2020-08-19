************************************************************************
file with basedata            : md376_.bas
initial value random generator: 1274544648
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  155
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       28        8       28
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1          16
   3        3          3           5   6   7
   4        3          3           8  10  16
   5        3          2          17  21
   6        3          1          20
   7        3          3           8   9  10
   8        3          2          15  19
   9        3          3          12  13  17
  10        3          1          11
  11        3          3          13  14  17
  12        3          3          14  16  19
  13        3          2          15  20
  14        3          2          15  18
  15        3          1          21
  16        3          2          18  20
  17        3          1          19
  18        3          1          21
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       7    7    6    8
         2     5       5    6    5    6
         3     7       3    3    5    4
  3      1     2       6    5    3    8
         2     5       6    3    3    7
         3     6       6    2    2    7
  4      1     1       8    7    5    9
         2     5       7    5    4    7
         3     5       7    7    3    7
  5      1     1       2    9   10    3
         2     4       2    8    9    3
         3    10       2    8    8    3
  6      1     4       5    8    7    6
         2     7       4    4    7    4
         3     7       4    6    6    4
  7      1     4       3    6    9    8
         2     7       3    4    8    7
         3     9       3    4    7    6
  8      1     2       2    8    3    9
         2     5       2    7    3    7
         3    10       1    6    2    6
  9      1     3       4    5    3    5
         2     6       3    5    3    3
         3     9       2    5    2    3
 10      1     5      10    7    5    3
         2     5       7    6    4    5
         3     7       7    6    4    2
 11      1     1       9    8    3    6
         2     6       5    5    3    2
         3     6       4    2    3    4
 12      1     4       5    8    4   10
         2     6       5    7    4    6
         3     8       5    5    1    3
 13      1     1       6    8    2    5
         2     3       5    6    2    5
         3     9       2    5    1    5
 14      1     2      10    9    9    5
         2     7       6    9    8    4
         3     8       4    8    7    4
 15      1     2       7    9   10    8
         2     3       6    8   10    8
         3     6       6    6    9    8
 16      1     1       8    6    7    8
         2     4       4    5    5    7
         3     7       4    4    2    5
 17      1     2       5    8    7    8
         2     4       4    8    7    5
         3     6       2    6    6    2
 18      1     7       4    8    5    7
         2     9       4    6    5    4
         3    10       3    5    1    2
 19      1     1       8    6    7    6
         2     4       8    6    5    5
         3     5       8    4    5    1
 20      1     6       9    8    9    6
         2     6      10   10    8    4
         3    10       6    3    6    1
 21      1     6       6    4    7    7
         2    10       3    4    6    6
         3    10       1    4    6    7
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   28   27  112  123
************************************************************************
