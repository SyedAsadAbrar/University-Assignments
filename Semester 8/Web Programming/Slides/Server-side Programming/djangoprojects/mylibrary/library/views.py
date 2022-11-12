# Create your views here.
from django.shortcuts import render
from django.http import HttpResponse
from library.models import User,Book

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
	booksData = ''
	counter = 0
	for book in books:		
		booksData += 'datasource[' + str(counter) +'] = ["' + book.title + '","' + book.getAuthorNames() + '"];'
		counter += 1
		
	return render(request,'library/results.html',{'result' : booksData, 'query' : request.GET["q"] })
	
def template(request):
	return render(request,'library/template.html',{})   