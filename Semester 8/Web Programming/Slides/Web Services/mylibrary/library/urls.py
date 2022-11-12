from django.urls import path
from . import views

urlpatterns = [
				path('',views.index,name='index'),				
				path('login',views.login,name='login'),
				path('authenticate',views.authenticate,name='authenticate'),
				path('logout',views.logout,name='logout'),
				path('search',views.search,name='search'),
            path('api/search',views.searchapi,name='searchapi'),
            path('template',views.template,name='template'),
			  ]