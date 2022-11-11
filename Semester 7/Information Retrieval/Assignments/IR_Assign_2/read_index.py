from krovetzstemmer import Stemmer
import sys
import os
import linecache

#sys.argv = ["read_index.py", "--term", "uss"]

if(sys.argv[1] == "--term" and len(sys.argv) == 3):
    term = sys.argv[2]
    kStemmer = Stemmer()

    with open("termids.txt", "r") as f:
        lines = f.read().splitlines()

    lines = [x[x.index('\t') + 1:] for x in lines]

    termStemmed = kStemmer.stem(term)

    if termStemmed in lines:
        index = lines.index(termStemmed) + 1
        list = linecache.getline("term_index.txt", index)
        list = list.split()
        print("\nListing for term: " + term)
        print("TERMID: " + list[0])
        print("Number of documents containing term: " + list[2])
        print("Term frequency in corpus: " + list[1])

else:
    print("Wrong syntax is used. The correct syntax is \"read_index.py --term TERM\" where TERM is the word you want look up. Please run the program again.")