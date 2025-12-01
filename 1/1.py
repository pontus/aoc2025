#!/usr/bin/env python

dial: int = 50

counter: int = 0

with open("input.txt") as f:
    while True:
        l = f.readline().strip()
        if not l:
            break
        print(l)

        factor = 1
        if l[0] == "L":
            factor = -1

        diff = int(l[1:]) * factor
        dial = (dial + diff) % 100

        if dial == 0:
            counter += 1

print(counter)
