# Quantel Assignment - TEST 1. Kind of FizzBuzz

This problem is a simple print numbers from 0 to 100, but with a twist.

The twist is: if the number contains 3 (like 3, 13, 30, etc) print "Fizz" instead of the number. Also, if the number contains 5 print "Buzz". If the number contains both 3 and 5, print "FizzBuzz". 


## Run

please use `lein run` command to check output.


## Solution:

This is simplest solution for FizzBuzz problem and involves number to string transformation. Then string transformed to a set of characters, this dramatically simplifys compartions. Note, that `cond` operation stops computation when first clause matches `true`, that's why #{\3 \5} set compartion should be first to match.

### Other solution

This problem can also be solved without number to string conversion using math. Because we have only first 100 numbers, we can make division and compare integer reminders with 3 and 5, but I believe this will be less simple and elegant then solution with set operations.


## Testing:

This solution has simple tests for `fizz-buzz` function.

Please, use `lein test` to run test suite.
