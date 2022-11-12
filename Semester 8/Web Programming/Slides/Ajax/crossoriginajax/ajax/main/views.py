from django.shortcuts import render
from django.template import loader
from django.http import HttpResponse

# Create your views here.
def index(request):
    return render(request,'main/index.html',{})
    
def xml(request):    
    return render(request,'main/students.xml',{},content_type='text/xml');
    
def jsonp(request):
    template = loader.get_template('main/students.xml')
    content = template.render({},request)
    content = content.replace('\n','').replace('\r','')
    callback = request.GET['jsonp']
    content = '' + callback + '("' + content + '");'
    
    return HttpResponse(content)
    
    
