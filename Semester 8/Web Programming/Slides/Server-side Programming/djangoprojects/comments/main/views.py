from django.shortcuts import render, redirect
from .models import *

# Create your views here.
def index(request):
    comments = Comment.objects.all()
    return render(request,'main/index.html',{'comments': comments})

def save(request):
    if request.POST['id'] != '':
        comment = Comment.objects.filter(id=request.POST['id'])
        comment.update(text=request.POST['comment'])
    else:
        comment = Comment(text=request.POST['comment'])
        comment.save()    
    return redirect('main:index')
    
def delete(request):
    comment = Comment.objects.filter(id=request.GET['id'])
    comment.delete()    
    return redirect('main:index')