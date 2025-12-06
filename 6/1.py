#!/usr/bin/env python3


def texttolist(s):
    v = s.strip().split()

    return v


def getnums(p):
    return [[int(y) for y in x] for x in p]


def times(*a):
    c = 1
    for p in a:
        c *= p
    return c


def plus(*a):
    c = 0
    print(a)
    for p in a:
        c += p
    return c


def getop(x):
    if x == "+":
        return plus
    return times


def getops(p):
    return [getop(x) for x in p]


f = open("input.txt")
l = f.readlines()

all = [texttolist(x) for x in l]

nums = getnums(all[:-1])
ops = getops(all[-1])

# print(f"Lista 999 {list(map(lambda x: x[999], nums))}")
# print(f"ops 996 {ops[996]}")
vals = []
for c in range(len(ops)):
    vals.append(ops[c](*list(map(lambda x: x[c], nums))))

print(plus(*vals))
print(vals)
