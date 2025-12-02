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

            if l % 2 == 0 and s[int(l / 2) :] == s[: int(l / 2)]:
                print(f"adding {idnum}")
                sum += int(idnum)

print(sum)
