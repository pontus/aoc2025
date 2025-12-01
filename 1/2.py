#!/usr/bin/env python

dial: int = 50

counter: int = 0

with open("input.txt") as f:
    while True:
        l = f.readline().strip()
        if not l:
            break

        direction = 1
        if l[0] == "L":
            direction = -1

        movement = int(l[1:])
        counter += int(movement / 100)
        movement = movement % 100

        olddial = dial
        diff = direction * movement
        dial = dial + diff

        if olddial != 0 and (dial <= 0 or dial >= 100):
            counter += 1

        dial = dial % 100

print(counter)
