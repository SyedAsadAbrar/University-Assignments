from django.urls import path
from . import views

app_name = 'main'
urlpatterns = [
    path('', views.index, name='index'),
    path('save', views.save, name='save'),
    path('delete', views.delete, name='delete'),
]
