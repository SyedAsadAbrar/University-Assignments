from django.db import models
from django.db import connections

# Create your models here.


class User(models.Model):
		
	def __init__(self, uname):
		self.username = uname

	def authenticate(self,pwd):
		with connections['mysql'].cursor() as cursor:
			cursor.execute("select * from users where username = %s",[self.username])			
			rows = cursor.fetchone()						
			
			if(rows[3] == pwd):
				return True
		
		return False
		
class Book(models.Model):
		
	def __init__(self, i, t,isb):
		self.id = i
		self.title = t
		self.isbn = isb
		self.authors = []
		
	def getBooks(t):
		books = []
		booksTable = {}
		with connections['mysql'].cursor() as cursor:
			cursor.execute(" select id,title,isbn from books where title like %s ", \
							["%" + t + "%"])
			
			rows = cursor.fetchall()
			for row in rows:
				book = Book(row[0],row[1],row[2])
				books.append(book)
				booksTable[row[0]] = book 
				
			cursor.execute(" select books.id,authors.name " \
							" from books,authors,books_authors " \
							" where books.id = books_authors.book_id " \
							" and authors.id = books_authors.author_id " \
							" and books.title like %s ", \
							["%" + t + "%"])
						 
			rows = cursor.fetchall()
			
			for row in rows:
				booksTable[row[0]].authors.append(row[1])
				
		return books

	def getAuthorNames(self):
		authorNames = ''
		for author in self.authors:
			authorNames += author + ","
		return authorNames
