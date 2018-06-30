# python3

import sys

sizes = [10,0,5,0,3,3]
transactions = [[6,6],[6,5],[5,4],[4,3]]

maxSize = 0
tables = []

class Table:

    def __init__(self, size, parent):
        self.size = size
        self.parent = parent

def getParent(i):
    if (i != tables[i].parent ) :
        tables[i].parent = getParent(tables[i].parent)
    return tables[i].parent


def merge(destination, source):
    realDestination, realSource = getParent(destination), getParent(source)

    if realDestination != realSource:
        srcTable = tables[realSource]
        destTable = tables[realDestination]
        destTable.size += srcTable.size
        srcTable.size = 0
        srcTable.parent = realDestination
        global maxSize
        maxSize = max(maxSize, destTable.size)

    print maxSize


for index, size in enumerate(sizes):
    tables.append(Table(sizes[index], index))
    maxSize = max(maxSize, sizes[index])

# merge the tables
for index, transaction in enumerate(transactions):
    destination, source = transaction
    merge(destination - 1, source - 1)
    


"""
table = Table(100, 10)
print table.parent

a = []
a.append(table)

m =  a[0]
print m.size
"""