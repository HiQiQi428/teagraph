import os

path = os.getcwd()
path = os.path.join(path, '../src/main/java/org/luncert/cypher/grammar/')
for fileName in os.listdir(path):
    if fileName.endswith('.java'):
        fileName = os.path.join(path, fileName)
        data = None
        with open(fileName, 'rb') as f:
            data = f.read()
            data = 'package org.luncert.cypher.grammar;\n' + data
        with open(fileName, 'wb') as f:
            f.write(data)