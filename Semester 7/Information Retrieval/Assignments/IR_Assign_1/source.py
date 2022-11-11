import os
from bs4 import BeautifulSoup
from nltk.tokenize import word_tokenize
import re
from krovetzstemmer import Stemmer
from operator import itemgetter
import time
import sys

# to allow file names with spaces;
# " " is the separator for the joining
# of the arguments separated due to
# the spaces in between the directory
# address, this approach works for
# non-spaced directory names too
directory = " ".join(sys.argv[1:])

docid = 1
termid = 1

terms = {}

# no hashmap terms_index list of tuples
terms_index = []

# hashmap (dictionary) implementation
terms_dict = {}

fDoc = open("docids.txt", "w")
fStop = open("stoplist.txt", "r")
# opening file to read
fTerm = open("termids.txt", "w")

start_time = time.time()

for filename in os.listdir(directory):

	# print(filename)
	with open(os.path.join(directory, filename), 'r', errors='ignore') as f:
		text = f.read()
		# print(text)

		# saving document id to docids file
		fDoc.write(str(docid) + "\t" + filename + "\n")
		# docid += 1

		# to remove headers at the beginning of the file
		if (text.find("<!DOCTYPE ") != -1):
			text = text[(text.find("<!DOCTYPE ")) + len("<!DOCTYPE "):]
			text = text[(text.find(">")) + 1:]

		if (text.find("<html") != -1):
			text = text[(text.find("<html")) + len("<html"):]
			text = text[(text.find(">")) + 1:]

		soup = BeautifulSoup(text, 'lxml')

		# to remove JS scripts
		for script in soup("script"):
			script.extract()

		for style in soup("style"):
			style.extract()

		# not sure if I need to handle other tags(?), that would be hard-coding

		# actual parsing of file for text
		text = soup.get_text(separator=' ')

		# to remove non-alphabetical letters and apostrophes
		# text = re.sub(r'[^A-Za-z-\s]', '', text)
		text = re.sub(r'[^A-Za-z\']', '\n', text)

		# removing apostrophes previously ignored
		text = re.sub(r'\'', '', text)

		# tokenizing separate words
		text = word_tokenize(text)

		dictOfWords = {i: text[i] for i in range(0, len(text))}

		# reading stopword list
		stop_words = fStop.read()

		# tokenizing words into a list
		stop_words = word_tokenize(stop_words)

		# initializing kStem stemmer object
		kStemmer = Stemmer()

		for i in range(len(text)):
			# converting to lower-case
			dictOfWords[i] = dictOfWords[i].lower()
			# applying stop-wording
			if dictOfWords[i] not in stop_words:
				# stemming
				dictOfWords[i] = kStemmer.stem(dictOfWords[i])
				if dictOfWords[i] not in terms:
					terms[dictOfWords[i]] = termid
					fTerm.write(str(termid) + "\t" + dictOfWords[i] + "\n")
					termid += 1

				# for term_index file
				# no hashmap
				# storing in a tuple format
				# <termid, docid, position>
				terms_index.append((terms[dictOfWords[i]], docid, i + 1))

				# hashmap code
				if (terms[dictOfWords[i]]) not in terms_dict:
					terms_dict[terms[dictOfWords[i]]] = []

				terms_dict.get(terms[dictOfWords[i]]).append([docid, i + 1])

		docid += 1


# sorting tuples by termid
terms_index = sorted(terms_index, key=itemgetter(0, 1, 2))

terms_index = dict.fromkeys(terms_index, 1)

fHashmap = open("term_index.txt", "w")

for i in range(1, len(terms_dict)):
	x = terms_dict[i]
	fHashmap.write(str(i) + " " + str(len(x)) + " ")

	prevDoc = x[0][0]
	prevPos = x[0][1]

	for j in range(1, len(x)):
		nextDoc = x[j][0]
		nextPos = x[j][1]
		temp1 = nextDoc
		temp2 = nextPos
		nextDoc -= prevDoc
		if nextDoc == 0:
			nextPos -= prevPos
		x[j][0] = nextDoc
		x[j][1] = nextPos
		prevDoc = temp1
		prevPos = temp2

	count = sum(1 for j in x if j[0] != 0)
	fHashmap.write(str(count) + " ")
	for j in range(len(x)):
		fHashmap.write(str(x[j][0]) + "," + str(x[j][1]) + " ")
	fHashmap.write("\n")

term_info = []

f = open("term_index.txt", "w")

# writing term_index file for list index
for iterator in range(1, len(terms)):
	term_info = [x for x in terms_index.keys() if x[0] == iterator]

	prevDoc = term_info[0][1]
	prevPos = term_info[0][2]

	for x in term_info[1:]:
		y = list(x)
		nextDoc = y[1]
		nextPos = y[2]
		y[1] -= prevDoc
		if y[1] == 0:
			y[2] -= prevPos
		prevDoc = nextDoc
		prevPos = nextPos
		term_info[term_info.index(x)] = tuple(y)

	f.write(str(iterator) + " " + str(len(term_info)) + " ")
	count = sum(1 for x in term_info if x[1] != 0)
	f.write(str(count) + " ")
	for j in range(len(term_info)):
		f.write(str(term_info[j][1]) + "," + str(term_info[j][2]) + " ")
	f.write("\n")
	term_info = []
