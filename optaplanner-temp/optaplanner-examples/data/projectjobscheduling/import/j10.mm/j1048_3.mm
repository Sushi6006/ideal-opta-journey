************************************************************************
file with basedata            : mm48_.bas
initial value random generator: 1513848612
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  70
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       13        2       13
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1           6
   3        3          3           5  10  11
   4        3          3           6   7   8
   5        3          1           9
   6        3          2           9  10
   7        3          1          11
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
  2      1     2      10    7    9    8
         2     2       9    8    9    8
         3     4       7    6    9    7
  3      1     6       7    5    7    6
         2     9       6    4    7    4
         3    10       5    4    2    2
  4      1     5       6    8    7    2
         2     6       6    7    5    1
         3     6       5    7    6    1
  5      1     4       8    9    8    7
         2     5       5    5    7    7
         3    10       1    2    7    6
  6      1     1      10    4    8   10
         2     6      10    4    7    9
         3     8      10    3    6    8
  7      1     2       5   10    8    7
         2     5       4    8    7    6
         3    10       4    7    6    5
  8      1     2       9    7    4   10
         2     2       6    9    4    8
         3     5       4    3    4    7
  9      1     3       3    5    5    7
         2     7       3    4    3    7
         3     9       2    2    3    5
 10      1     1       6    9   10    8
         2     1       9    8    9    7
         3     6       4    8    6    4
 11      1     1       6    4    9    9
         2     1       5    3    8   10
         3     2       4    1    1    8
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   21   18   75   75
************************************************************************
