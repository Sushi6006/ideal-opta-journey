************************************************************************
file with basedata            : mf42_.bas
initial value random generator: 2025312216
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  32
horizon                       :  240
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     30      0       22       15       22
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1          14
   3        3          3           5  10  11
   4        3          3           6   7   8
   5        3          2          13  26
   6        3          1           9
   7        3          2          16  17
   8        3          3          12  18  21
   9        3          2          16  24
  10        3          3          23  29  31
  11        3          3          15  18  25
  12        3          3          17  20  30
  13        3          1          27
  14        3          2          15  16
  15        3          2          22  23
  16        3          3          20  22  25
  17        3          2          19  26
  18        3          3          19  22  24
  19        3          1          28
  20        3          1          31
  21        3          2          24  30
  22        3          1          28
  23        3          1          30
  24        3          2          27  29
  25        3          2          26  27
  26        3          1          29
  27        3          1          28
  28        3          1          31
  29        3          1          32
  30        3          1          32
  31        3          1          32
  32        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       0    6    8    7
         2     1      10    0    7    8
         3     4       3    0    5    6
  3      1     6       4    0    6   10
         2     6       0    5    7    8
         3     8       0    3    5    6
  4      1     4       0   10    7    9
         2     4      10    0    8    9
         3     6       6    0    7    9
  5      1     1       8    0    4    6
         2     2       0    7    4    6
         3    10       0    6    4    5
  6      1     2       0    9    9    9
         2     3       8    0    8    9
         3    10       0    7    8    6
  7      1     3       0    9    8    9
         2     7       8    0    7    9
         3     7       0    1    7    8
  8      1     2       0    9    3    9
         2     5       4    0    2    6
         3     8       0    8    2    3
  9      1     6       0    4    9    7
         2     9       4    0    5    4
         3     9       0    2    6    5
 10      1     6       0   10    7    7
         2     8       0    7    3    7
         3     9       0    7    2    7
 11      1     4       5    0    8    6
         2     6       0    7    7    3
         3     7       5    0    3    3
 12      1     1       8    0    4    8
         2     1      10    0    3   10
         3     2       3    0    2    5
 13      1     2       0    4    8    8
         2     7       0    4    7    8
         3    10       0    1    6    7
 14      1     1       0    6    8    5
         2     2       0    3    5    4
         3    10       0    3    3    4
 15      1     2       0    5    8    5
         2     5       0    5    6    2
         3    10       0    5    4    1
 16      1     1       0   10    5    8
         2     3       3    0    4    5
         3     6       0   10    3    3
 17      1     4       0    9    9    6
         2     4       8    0    9    6
         3    10       6    0    4    6
 18      1     1       0    7    7    4
         2     5       0    6    6    2
         3    10       0    6    3    1
 19      1     1       7    0    6    8
         2     5       6    0    5    6
         3    10       0    3    3    6
 20      1     4       8    0    8   10
         2     7       0    9    5    6
         3     9       0    5    4    4
 21      1     5       0    5    7    8
         2     7       0    4    5    7
         3     8       5    0    4    5
 22      1     1       0    9    8    8
         2     2       0    4    6    8
         3     7       1    0    6    6
 23      1     4       0    8   10    8
         2     6       0    5   10    8
         3     9       8    0    9    5
 24      1     3       0    3    5    9
         2     6       5    0    4    6
         3    10       3    0    2    6
 25      1     4       0   10    6    8
         2     5       7    0    5    5
         3     6       0    9    3    3
 26      1     3       0    6    7    8
         2     5       0    5    5    5
         3     8       0    5    4    2
 27      1     2       7    0    2    7
         2     6       0    5    2    2
         3     6       6    0    1    1
 28      1     1       6    0    5    4
         2     3       4    0    5    4
         3     7       4    0    5    3
 29      1     2       7    0    6    9
         2     6       0    6    6    7
         3    10       0    4    5    5
 30      1     1       1    0    7    8
         2     4       0   10    5    4
         3     8       0   10    3    3
 31      1     1       6    0    8    6
         2     2       0    8    7    5
         3     6       0    8    4    5
 32      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   19   25  166  183
************************************************************************
