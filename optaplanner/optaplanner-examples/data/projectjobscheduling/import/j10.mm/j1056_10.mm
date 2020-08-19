************************************************************************
file with basedata            : mm56_.bas
initial value random generator: 759433392
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  75
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       14        8       14
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           5   6   7
   3        3          1           5
   4        3          2           6  10
   5        3          2           9  10
   6        3          2           8   9
   7        3          1           9
   8        3          1          11
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     5       4    5   10    6
         2     7       4    5    3    4
         3     7       4    4    6    4
  3      1     2       8    8    3    6
         2     3       8    6    2    3
         3     5       5    4    2    2
  4      1     3       5    8    9    9
         2     6       3    6    9    7
         3     7       1    5    9    3
  5      1     2      10    3    4    8
         2     5       9    2    4    7
         3     7       7    2    3    6
  6      1     3       6    6    9    9
         2     9       4    6    8    9
         3    10       2    5    8    6
  7      1     6       6    8    8    6
         2     7       2    8    2    4
         3     7       3    7    4    4
  8      1     3       4    7    5    4
         2     7       4    7    3    3
         3     7       3    7    3    4
  9      1     1       8    7    6    5
         2     6       8    7    4    5
         3     8       7    4    1    4
 10      1     7       6    3    6    4
         2     9       6    2    6    2
         3    10       5    2    6    2
 11      1     2      10    7    5    4
         2     2       6    9    5    4
         3     7       5    3    5    4
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   19   17   65   61
************************************************************************
