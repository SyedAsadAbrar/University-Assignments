from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('xml', views.xml),    
    path('jsonp', views.jsonp),    
]
