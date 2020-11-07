# SCAD P01 - Cloud Function Behaviour
By Samuel Bangslund (bangssam@students.zhaw.ch)

## E1: size of input data

## E2: configured memory allocation
The following data is produced by altering the allocated memory for the AWS lambda function. It varies from 128-2048 and the function does nearly no computation at all. The allocation would presumably be more effective if the function was more computation heavy.
| allocated | min | max  | mean | sum   |
|-----------|-----|------|------|-------|
| 128       | 445 | 3170 | 534  | 21914 |
| 256       | 438 | 3252 | 531  | 21246 |
| 512       | 442 | 3105 | 533  | 19725 |
| 1024      | 436 | 3187 | 527  | 21634 |
| 2048      |     |      |      |       |

## E3: function code size

## E7: external network calls
