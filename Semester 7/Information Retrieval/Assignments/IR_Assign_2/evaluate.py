from tabulate import tabulate

fDocs = open("docids.txt", "r")

docNames = fDocs.read()
docNames = docNames.split('\n')
docNames = [x.split('\t') for x in docNames if x != '']
namesDict = {docNames[i][1]:i + 1 for i in range(0, len(docNames))}

fQREL = open("relevance judgements.qrel", "r")
fOkapi = open("outputOkapi.txt", "r")
fDirichlet = open("outputDirichlet.txt", "r")
fReport = open("report.txt", "w")

corpus = fQREL.read()
okapi = fOkapi.read()
dirichlet = fDirichlet.read()

corpus = corpus.split('\n')
okapi = okapi.split('\n')
dirichlet = dirichlet.split('\n')

# to remove blank strings
corpus = [string for string in corpus if string != '']
okapi = [string for string in okapi if string != '']
dirichlet = [string for string in dirichlet if string != '']

corpusGrades = {}
okapiRank = {}
okapiGradesDict = {}
dirichletRank = {}
dirichletGradesDict = {}

# storing graded results from corpus
for i in range(len(corpus)):
	entry = corpus[i]
	entry = entry.split(' ')
	topic = int(entry[0])
	docName = entry[2]
	grade = int(entry[3])
	tuple = (topic, namesDict[docName])
	corpusGrades[tuple] = grade

oldTopic = ""

# storing graded results from okapi
for i in range(len(okapi)):
	entry = okapi[i]
	entry = entry.split(' ')
	topic = int(entry[0])
	docName = entry[1]
	rank = int(entry[2])
	if oldTopic != topic and i != 0:
		okapiGradesDict[oldTopic] = okapiRank
		okapiRank = {}
	okapiRank[rank] = namesDict[docName]
	oldTopic = topic

okapiGradesDict[oldTopic] = okapiRank

oldTopic = ""

# storing graded results from Dirichlet
for i in range(len(dirichlet)):
	entry = dirichlet[i]
	entry = entry.split(' ')
	topic = int(entry[0])
	docName = entry[1]
	rank = int(entry[2])
	if oldTopic != topic and i != 0:
		dirichletGradesDict[topic] = dirichletRank
		dirichletRank = {}
	dirichletRank[rank] = namesDict[docName]
	oldTopic = topic

dirichletGradesDict[topic] = dirichletRank

okapiPrecision = {}
tempPrecisionDict = {}
okapiMAP = 0
tempAP = 0
relevant = False
relevantCount = 0

for topic in okapiGradesDict.keys():
	val = 0
	number = 1
	found = 0
	for i in range (0, len(okapiGradesDict[topic])):
		tuple = (topic, okapiGradesDict[topic][i+1])
		if tuple in corpusGrades.keys() and corpusGrades.get(tuple) >= 1:
			found += 1
			relevant = True
			relevantCount += 1
		val = found/number
		if relevant:
			tempAP += val
			relevant = False
		if number <= 30:
			tempPrecisionDict[number] = val
		number += 1
	if relevantCount != 0:
		tempAP /= relevantCount
	okapiMAP = tempAP
	tempAP = 0
	relevantCount = 0
	relevant = False
	okapiPrecision[topic] = tempPrecisionDict
	tempPrecisionDict = {}

okapiMAP /= len(okapiGradesDict.keys())

dirichletPrecision = {}
tempPrecisionDict = {}
dirichletMAP = 0
tempAP = 0
relevant = False
relevantCount = 0

for topic in dirichletGradesDict.keys():
	val = 0
	number = 1
	found = 0
	for i in range(0, len(dirichletGradesDict[topic])):
		tuple = (topic, dirichletGradesDict[topic][i + 1])
		if tuple in corpusGrades.keys() and corpusGrades.get(tuple) >= 1:
			found += 1
			relevant = True
			relevantCount += 1
		val = found / number
		if relevant:
			tempAP += val
			relevant = False
		if number <= 30:
			tempPrecisionDict[number] = val
		number += 1
	if relevantCount != 0:
		tempAP /= relevantCount
	dirichletMAP = tempAP
	tempAP = 0
	relevantCount = 0
	relevant = False
	dirichletPrecision[topic] = tempPrecisionDict
	tempPrecisionDict = {}

dirichletMAP /= len(dirichletGradesDict.keys())

t = [["Query","P@5","P@10","P@20","P@30"]]
for key in okapiPrecision.keys():
	t.append([key, okapiPrecision[key][5], okapiPrecision[key][10], okapiPrecision[key][20], okapiPrecision[key][30]])

fReport.write("Okapi BM25\n\n" + tabulate(t, headers="firstrow") + "\n\nMAP: " + str(okapiMAP))

fReport.write('\n\n------------------------------------------\n\n')

t = [["Query", "P@5", "P@10", "P@20", "P@30"]]
for key in dirichletPrecision.keys():
	t.append([key, dirichletPrecision[key][5], dirichletPrecision[key][10], dirichletPrecision[key][20], dirichletPrecision[key][30]])

fReport.write("Dirichlet Smoothing\n\n" + tabulate(t, headers="firstrow") + "\n\nMAP: " + str(dirichletMAP))
