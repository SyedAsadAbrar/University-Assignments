import linecache
import math
import operator
import subprocess
from bs4 import BeautifulSoup
from nltk.tokenize import word_tokenize
from krovetzstemmer import Stemmer
import random
import os
import collections
import sys

#sys.argv = ["query.py", "--score", "Okapi", "BM25"]
sys.argv = ["query.py", "--score", "Dirichlet", "Smoothing"]

if sys.argv[1] == "--score" and len(sys.argv) == 4:

	if not os.path.isfile('doclengths.txt') or os.path.getsize('doclengths.txt') == 0:
		subprocess.call(
			["python", "./docLength.py", "E:/Fast/7th Semester/Information Retrieval/Assignments/Assignment 1/corpus"])

	fQueries = open("topics.xml", "r")
	fQREL = open("relevance judgements.qrel", "r")
	fStop = open("stoplist.txt", "r")
	fDocs = open("docids.txt", "r")
	fIndex = open("term_index.txt")
	fDocLengths = open("doclengths.txt", "r")

	docNames = fDocs.read()
	docNames = docNames.split('\n')
	docNames = [x.split('\t') for x in docNames if x != '']
	namesDict = {i + 1: docNames[i][1] for i in range(0, len(docNames))}

	fDocs = open("docids.txt", "r")

	docLengths = fDocLengths.read()
	docLengths = docLengths.split('\n')
	docLengths = [x.split('\t') for x in docLengths if x != '']
	lengthsDict = {i + 1: int(docLengths[i][1]) for i in range(0, len(docLengths))}

	avgDLen = sum(lengthsDict) / len(lengthsDict)

	# BM25 variable
	D = fDocs.read().split('\n')
	D = [x for x in D if x != '']
	D = len(D)

	# BM25 variable
	k1 = 1.2
	k2 = random.randrange(0, 1001, 1)
	b = 0.75

	# Dirichlet variable
	mu = avgDLen
	corpusSize = sum(lengthsDict)

	# reading stopword list
	stop_words = fStop.read()

	# tokenizing words into a list
	stop_words = word_tokenize(stop_words)

	# initializing kStem stemmer object
	kStemmer = Stemmer()

	queries = fQueries.read()
	corpus = fQREL.read()

	soup = BeautifulSoup(queries, 'lxml')
	queries = soup.find_all('topic')
	queriesDict = {}

	scoresDict = {}

	corpus = corpus.split('\n')

	# to remove blank strings
	corpus = [string for string in corpus if string != '']

	assessmentsDict = {}
	grades = {}

	# storing queries
	for i in range(len(queries)):
		query = queries[i]
		topic = int(query['number'])
		query = query.find('query').contents[0]
		tuple = (topic, query)
		queriesDict[i] = tuple

	# storing graded results from corpus
	for i in range(len(corpus)):
		entry = corpus[i]
		entry = entry.split(' ')
		topic = int(entry[0])
		docName = entry[2]
		grade = int(entry[3])
		tuple = (topic, docName)
		assessmentsDict[i] = tuple
		grades[i] = grade

	done = 0

	if sys.argv[2] == "Okapi" and sys.argv[3] == "BM25":
		# BM25
		# LOOP FOR QUERY
		for i in range(len(queriesDict)):
			tempScoreDict = {}

			topic = queriesDict[i][0]
			query = queriesDict[i][1]

			#splitting on white space
			query = query.split(' ')

			#converting tokens to lowercase
			query = [token.lower() for token in query]

			#applying stop-wording
			query = [token for token in query if token not in stop_words]

			#stemming
			query = [kStemmer.stem(token) for token in query]

			# for tf(d,i)
			termsDocDict = {}

			# for tf(q,i)
			termsQueryDict = dict(collections.Counter(query))
			df = {}

			# To get tf(d,i)
			for j in range(len(query)):
				# Getting termid of term
				termId = os.popen('python ./read_index.py --term ' + query[j]).read()

				if(termId != ''):
					termId = (termId.split('\n')[2]).split(' ')
					termId = int(termId[1])

					# Getting values from term_index file for term
					termsList = linecache.getline("term_index.txt", termId)
					termsList = termsList.split()

					# BM25 variable
					df[query[j]] = int(termsList[2])

					# BM25 variable
					# to get the frequencies of term in every document
					docsCount = [token.split(',') for token in termsList[3:]]
					docsCount = [(list(map(int, token[0])))[0] for token in docsCount]
					docsCount = [sum(docsCount[0:i+1]) for i in range(0, len(docsCount))]
					docsCount = dict(collections.Counter(docsCount))
					termsDocDict[query[j]] = docsCount

				else:
					# BM25 variable
					df[query[j]] = 0

					# BM25 variable
					docsCount = {-1: -1}
					termsDocDict[query[j]] = docsCount

			# LOOP FOR DOCUMENT
			for j in range(len(lengthsDict)):
				score = 0

				# BM25 variable
				# K is constant for a document i
				K = k1 * ((1 - b) + b * (lengthsDict[j+1] / avgDLen))

				# processing individual words in query
				for k in range(len(query)):
					firstProd = math.log2((D + 0.5)/(df[query[k]] + 0.5))

					# tfd => Term frequency in document
					tfd = termsDocDict[query[k]].get(j+1)
					if tfd == None:
						tfd = 0
					secondProd = ((1 + k1)*tfd)/(K + tfd)

					# tfq => Term frequency in query
					tfq = termsQueryDict.get(query[k])
					if tfq == None:
						tfq = 0
					thirdProd = ((1 + k2)*tfq)/(k2 + tfq)

					score += (firstProd * secondProd * thirdProd)

				# to limit answer to 2 decimal places
				multiplier = 10 ** 2
				score = math.ceil(score * multiplier) / multiplier
				tempScoreDict[j + 1] = score

			scoresDict[queriesDict[i][1]] = tempScoreDict

		done = 1

	if sys.argv[2] == "Dirichlet" and sys.argv[3] == "Smoothing":
		scoresDict = {}

		# Dirichlet Smoothing
		# for tf(c,i)
		termsCorpusDict = {}

		for i in range(len(queriesDict)):
			tempScoreDict = {}

			topic = queriesDict[i][0]
			query = queriesDict[i][1]

			#splitting on white space
			query = query.split(' ')

			#converting tokens to lowercase
			query = [token.lower() for token in query]

			#applying stop-wording
			query = [token for token in query if token not in stop_words]

			#stemming
			query = [kStemmer.stem(token) for token in query]

			# for tf(d,i)
			termsDocDict = {}

			# To get tf(d,i)
			for j in range(len(query)):
				# Getting termid of term
				termId = os.popen('python ./read_index.py --term ' + query[j]).read()

				if(termId != ''):
					termId = (termId.split('\n')[2]).split(' ')
					termId = int(termId[1])

					# Getting values from term_index file for term
					termsList = linecache.getline("term_index.txt", termId)
					termsList = termsList.split()

					# Dirichlet variable
					if(termsCorpusDict.get(query[j]) == None):
						termsCorpusDict[query[j]] = int(termsList[1])

					# Dirichlet variable
					# to get the frequencies of term in every document
					docsCount = [token.split(',') for token in termsList[3:]]
					docsCount = [(list(map(int, token[0])))[0] for token in docsCount]
					docsCount = [sum(docsCount[0:i+1]) for i in range(0, len(docsCount))]
					docsCount = dict(collections.Counter(docsCount))
					termsDocDict[query[j]] = docsCount

				else:
					# Dirichlet variable
					termsCorpusDict[query[j]] = 0

					# Dirichlet variable
					docsCount = {-1: -1}
					termsDocDict[query[j]] = docsCount

			# LOOP FOR DOCUMENT
			for j in range(len(lengthsDict)):
				score = 1
				N = lengthsDict[j+1]

				# processing individual words in query
				for k in range(len(query)):
					# tfd => Term frequency in document
					tfd = termsDocDict[query[k]].get(j + 1)
					if tfd == None:
						tfd = 0

					firstProd = tfd/(N+mu)

					# tfc => Term frequency in corpus
					tfc = termsCorpusDict.get(query[k])
					if tfc == None:
						tfc = 0
					secondProd = (mu/(N+mu))*(tfc/corpusSize)
					if(firstProd + secondProd != 0):
						score *= (firstProd + secondProd)

				# to limit answer to 2 decimal places
				#multiplier = 10 ** 2
				#score = math.ceil(score * multiplier) / multiplier
				tempScoreDict[j + 1] = score

			scoresDict[queriesDict[i][1]] = tempScoreDict

		done = 1

	if done == 1:
		i = 0

		fOutput = open("output.txt", "w")

		for key in scoresDict.keys():
			topic = queriesDict[i][0]

			sortedScores = sorted(scoresDict[key].items(), key=operator.itemgetter(1), reverse= True)

			# the first 30 documents with the highest scores are printed for each query
			for j in range(0, len(sortedScores)):
				docName = namesDict[sortedScores[j][0]]
				score = sortedScores[j][1]
				rank = j + 1

				fOutput.write(str(topic) + " " + docName + " " + str(rank) + " " + str(score) + " " + "run1\n")
			i += 1

else:
    print("Wrong syntax is used. The correct syntax is \"./query.py --score FUNC\" where FUNC is the function you want to use. Please run the program again.")