# Create your views here.
from django.shortcuts import render
from django.http import HttpResponse
from library.models import User,Book
from math import ceil

def index(request):	
	return render(request,'library/index.html',{})
	
def login(request):
	return render(request,'library/login.html',{})
	
def authenticate(request):	
	user = User(request.POST["username"])	
	if user.authenticate(request.POST["password"]):
		request.session["user"] = user
		return render(request,'library/admin.html',{})
	return render(request,'library/login.html',{})
	
def logout(request):
	request.session.pop("user")
	return render(request,'library/login.html',{})
	
def search(request):
	books = Book.getBooks(request.GET["q"])	
	#books = Book.getBooksFromGoogle(request.GET["q"])
	booksData = ''
	counter = 0
	for book in books:		
		booksData += 'datasource[' + str(counter) +'] = ["' + book.title + '","' + book.getAuthorNames() + '"];'
		counter += 1
		
	return render(request,'library/results.html',{'result' : booksData, 'query' : request.GET["q"] })

def searchapi(request):
	books = Book.getBooks(request.GET["q"])	
	page = int(request.GET["page"])
	pagesize = int(request.GET["pagesize"])
	maxpages = ceil(len(books) / pagesize)
	type = request.GET.get('type','xml')
	data = []
			
	for i in range((page-1)*pagesize,min(len(books),page*pagesize)):
		book = books[i]   
		data.append( {"title": book.title, "authors": book.getAuthorNames(), "isbn": book.isbn} )

	if type == 'json':
		return render(request,'library/results.json',{'rows' : data, 'maxpages' : maxpages }, content_type='application/json')

	return render(request,'library/results.xml',{'rows' : data, 'maxpages' : maxpages }, content_type='text/xml')
	
def template(request):
	return render(request,'library/template.html',{})   