************************************************************************
file with basedata            : mm51_.bas
initial value random generator: 4146547
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  79
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       29        9       29
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           9  10  11
   3        3          2           6   8
   4        3          1           5
   5        3          1           7
   6        3          1          11
   7        3          2           8  11
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
  2      1     1       5    4    0    2
         2     5       4    2    5    0
         3     5       5    3    4    0
  3      1     4       6    4    0    5
         2     9       5    4    4    0
         3     9       6    3    0    3
  4      1     5      10    7    6    0
         2     8      10    7    5    0
         3     9      10    7    4    0
  5      1     3       8    4    3    0
         2     4       5    4    0    8
         3     5       5    2    3    0
  6      1     7       8    9    0    8
         2     8       7    6    0    8
         3    10       6    3    0    8
  7      1     8       4    3    0    5
         2     8       4    3    2    0
         3     9       3    2    0    5
  8      1     7       6    9    9    0
         2     9       5    6    9    0
         3    10       5    4    0    3
  9      1     6       9    3    7    0
         2     7       9    3    0    5
         3     7       9    3    6    0
 10      1     4       7   10    0    6
         2    10       7    4    0    5
         3    10       7    3    9    0
 11      1     4       6    6   10    0
         2     4       9    5    0    2
         3     5       4    3   10    0
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   18   13   40   33
************************************************************************