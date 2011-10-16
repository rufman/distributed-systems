 #!/usr/bin/python
__author__ = 'basca'

import os

CWD = os.getcwd()
LIB_DIR = os.path.join(CWD, 'lib')
LIB_JARS = [os.path.join(LIB_DIR,jar_file) for jar_file in os.listdir(LIB_DIR) if jar_file.endswith('.jar')]

name = raw_input('enter your name and press [enter]:')
snumber = raw_input('enter your snumber and press [enter]:')
name = name.lower().replace(' ','-')

solution_title = '%s_%s_%s.jar'%(name, snumber, 'A1')

print "Compiling..."
os.system('javac -cp .:%s ./DSA1/src/edu/ch/uniz/ds2011/a1/*.java'%':'.join(LIB_JARS))
print "Packing..."
os.system('jar -cf %s -C ./DSA1/src/ .'%solution_title)
print "Your solution is available under: ", solution_title