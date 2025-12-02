#!/usr/bin/env python

sum: int = 0

with open("input.txt") as f:
    lineparts = f.readline().strip().split(",")
    for seq in lineparts:
        seqparts = seq.split("-")
        start = int(seqparts[0])
        end = int(seqparts[1])

        for idnum in range(start, end + 1):

            s = str(idnum)
            l = len(s)

            for i in range(1, l):

                if l % i == 0:
                    # Unless is an even divisor no point in checking this

                    segment = s[:i]
                    if s == segment * int(l / i):
                        print(f"adding {idnum}")
                        sum += int(idnum)
                        break
print(sum)
