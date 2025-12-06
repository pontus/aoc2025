#!/usr/bin/env python3


def texttolist(s):
    v = s.strip().split("-")

    if v[1] < v[0]:
        raise Error("vafalls")
    return [int(v[0]), int(v[1])]


def joinslots(v):
    if len(v) < 2:
        return v

    print(v)
    if v[0][1] >= v[1][0]:
        return [[v[0][0], v[1][1]]] + joinslots(v[2:])
    return [v[0]] + joinslots(v[1:])


f = open("input.txt")
l = f.readlines()
cut = l.index("\n")
freshtext = l[:cut]
fresh = [texttolist(x) for x in freshtext]
fresh.sort()
print(f"fresh sorted: {fresh}")

c = 0
while c != len(fresh):
    print("loop")
    c = len(fresh)
    fresh = joinslots(fresh)

print(fresh)
print(len(fresh))

sum = 0
for p in fresh:
    print(p)
    sum += p[1] - p[0] + 1

print(sum)
