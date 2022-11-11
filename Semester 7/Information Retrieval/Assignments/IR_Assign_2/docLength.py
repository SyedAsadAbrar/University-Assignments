import os
import sys
from bs4 import BeautifulSoup
import re

from krovetzstemmer import Stemmer
from nltk import word_tokenize

#sys.argv = ["docLength.py", "E:/Fast/7th Semester/Information Retrieval/Assignments/Assignment 1/corpus"]

if(len(sys.argv) >= 2):

	# to allow file names with spaces;
	# " " is the separator for the joining
	# of the arguments separated due to
	# the spaces in between the directory
	# address, this approach works for
	# non-spaced directory names too
	directory = " ".join(sys.argv[1:])

	i = 1

	fStop = open("stoplist.txt", "r")
	fLen = open("doclengths.txt", "w")

	for filename in os.listdir(directory):
		# print(filename)
		with open(os.path.join(directory, filename), 'r', errors='ignore') as f:
			text = f.read()

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

		# reading stopword list
		stop_words = fStop.read()

		# tokenizing words into a list
		stop_words = word_tokenize(stop_words)

		# initializing kStem stemmer object
		kStemmer = Stemmer()

		dictOfWords = {i: kStemmer.stem(text[i].lower()) for i in range(0, len(text)) if text[i] not in stop_words}

		print(str(i) + "\t" + str(len(dictOfWords)) + "\n")
		fLen.write(str(i) + "\t" + str(len(dictOfWords)) + "\n")
		i += 1
else:
	print("Wrong syntax is used. The correct syntax is \"docLength.py DIRECTORY\" where DIRECTORY is the address of the corpus. Please run the program again.")