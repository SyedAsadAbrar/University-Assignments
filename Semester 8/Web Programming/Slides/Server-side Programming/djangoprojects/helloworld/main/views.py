from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def index(request):
	list = ['Asad','Saad', 'Hina', 'Hadi']	
	return render(request,'main/index.html',{'names':list})