#include"GP142.h"
#include"Shape.h"
#include <fstream>
#include <iostream>
#include <string>
using namespace std;
#define FALSE 0
#define TRUE  1
Shape** extendBy1(Shape** AllShapes, int & size, int csize)
{
	if(csize==size)
	{
		Shape** temp=new Shape*[size*2];
		for (int i=0;i<size;i++)
		{
			temp[i]=AllShapes[i];
		}
		delete [] AllShapes;
		size=size*2;
		return temp;	
	}
	else
	{
		return AllShapes;
	}
}
Shape** sizecorrect(Shape ** AllShapes, int & size, int csize)
{
	if(csize<size/2)
	{
		Shape **temp=new Shape * [size/2];
		for (int i=0;i<csize+1;i++)
		{
			temp[i]=AllShapes[i];
		}
		delete [] AllShapes;
		size=size/2;
		return temp;
	}
	else
	{
		return AllShapes;
	}
}
Shape ** shrinkBy1(Shape ** AllShapes, int & size, int index)
{
	Shape ** temp = new Shape*[size];
	int iterator=0;
	for (int i=0;i<size;i++)
	{
		if (i!=index)
		{
			temp[iterator]=AllShapes[i];
			iterator++;
		}
	}
	delete [] AllShapes;
	return temp;
}
int main()
{
	int quit;                   /* Track whether the user has asked to quit */
	int event;                  /* Holds GP142 events                       */
	int mouse_x, mouse_y;       /* Not used in this program                 */
	char key_pressed;           /* Not used in this program                 */
	bool click_buttons= false;
	bool firstclick=false;
	int temp_x;
	int temp_y;
	int choice=-1;
	int color=0;	//default color is BLACK
	int start_y=304;
	int load_iterator=1;
	int load_size;
	bool click_screen=false;
	bool style=true;
	bool colorchange=false;
	bool load=false;
	Shape ** allShapes;
	int arrsize=1;
	int csize=0;
	allShapes=new Shape*[1];
	int fill=WHITE;
	Point temp1;
	Point temp2;
	vector <Point> temp;
	string shape_type;

	/* Open a blank GP142 graphics window.*/
	GP142_open();
	loadMenu();
	event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
	if (mouse_y<=-71 && mouse_y>=-105)
	{
		if (mouse_x>=-210 && mouse_x<=-160)
		{
			load=true;
		}
		else if (mouse_x>=30 && mouse_x<=80)
		{
			load=false;
		}
	}
	for (int j=-304;j<307;j++)
	{
		GP142_lineXY(WHITE,-625,j,662,j,3);
	}
	if (load==true)
	{
		ifstream read("drawing.pb");
		read>>load_size;
		while(load_iterator<=load_size)
		{
			read>>shape_type;
			if (shape_type=="Line")
			{
				allShapes[csize]=new Line;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Triangle")
			{
				allShapes[csize]=new Triangle;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Circle")
			{
				allShapes[csize]=new Circle;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Text")
			{
				allShapes[csize]=new Text;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Polygon")
			{
				allShapes[csize]=new PolygonS;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Curve")
			{
				allShapes[csize]=new Curve;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
			if (shape_type=="Rectangle")
			{
				allShapes[csize]=new RectangleS;
				allShapes[csize]->load(read);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				load_iterator++;
			}
		}
	}
	load=false;
	DisplayMenu();
	/*
	* Main event loop:
	* ---- ----- -----
	* All GP142 programs need to have an "event loop", which is simply a 
	* while loop that repeatedly gets events and then calls appropriate
	* functions in response.  This program ignores all events except the
	* GP142_QUIT event.
	*/
	quit = FALSE;
	while (!quit) {

		/* Get the next event */
		event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);

		/* Decide what kind of event we got */
		switch (event) {
		case GP142_QUIT:
			quit = TRUE;
			break;

		case GP142_MOUSE:
			if (colorchange==true)
			{
				DisplayMenu();
				colorchange=false;
			}
			if (click_buttons==true)
			{
				DisplayMenu();
				click_buttons=false;
			}
			if (mouse_x>-632)
			{
				click_screen=true;
			}
			if ((mouse_x>=-665 && mouse_x<=-632) && (mouse_y<=start_y && mouse_y>=start_y-352))
			{
				temp_y=mouse_y;
				if (temp_y<=start_y && temp_y>(start_y-13))
				{
					choice=1; //solid line
					style=true;
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<(start_y-15) && temp_y>(start_y-30))
				{
					choice=1; //dotted line
					style=false;
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<(start_y-35) && temp_y>=(start_y-65))
				{
					choice=2; //rectangle
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-70 && temp_y>=start_y-100)
				{
					choice=3; //triangle
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-105 && temp_y>=start_y-136)
				{
					choice=4; //circle
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-143 && temp_y>=start_y-173)
				{
					choice=5; //polygon
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-179 && temp_y>=start_y-190)
				{
					choice=6; //curve
					style=true;
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-195 && temp_y>=start_y-208)
				{
					choice=6; //curve
					style=false;
					click_buttons=true;
					lightUpButton(choice);			
				}
				else if (temp_y<start_y-212 && temp_y>=start_y-244)
				{
					choice=7; //text
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-251 && temp_y>=start_y-280)			
				{
					choice=8; //eraser
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-287 && temp_y>=start_y-316)
				{
					choice=9; //bucket
					click_buttons=true;
					lightUpButton(choice);
				}
				else if (temp_y<start_y-324 && temp_y>=start_y-352)
				{
					choice=10; //save
					click_buttons=true;
					lightUpButton(choice);
				}
			}
			else if (mouse_x>=-665 && mouse_x<=-632)
			{
				if (mouse_y<start_y-358 && mouse_y>=start_y-390)
				{
					color=2; //red
					lightUpButton(11);
					colorchange=true;
				}
				else if (mouse_y<start_y-397 && mouse_y>=start_y-426)
				{
					color=3; //green
					lightUpButton(12);
					colorchange=true;
				}
				else if (mouse_y<start_y-431 && mouse_y>=start_y-461)
				{
					color=4; //blue
					lightUpButton(13);
					colorchange=true;
				}
				else if (mouse_y<start_y-466 && mouse_y>=start_y-498)
				{
					color=5; //yellow
					lightUpButton(14);
					colorchange=true;
				}
				else if (mouse_y<start_y-503 && mouse_y>=start_y-534)
				{
					color=13; //orange
					lightUpButton(15);
					colorchange=true;
				}
				else if (mouse_y<start_y-539 && mouse_y>=start_y-570)
				{
					color=1; //white
					lightUpButton(16);
					colorchange=true;
				}
				else if (mouse_y<start_y-575 && mouse_y>=start_y-604)
				{
					color=0; //black
					lightUpButton(17);
					colorchange=true;	
				}
			}
			if (choice==1 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp_y=mouse_y;
				temp_x=mouse_x;
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=temp_x;
				temp1.y=temp_y;
				temp2.x=mouse_x;
				temp2.y=mouse_y;
				allShapes[csize]= new Line(color,2,style,temp1,temp2);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				click_screen=false;
				choice=-1;
				style=true;
				color=BLACK;
				DisplayMenu();
			}
			if (choice==2 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp2.x=mouse_x;
				temp2.y=mouse_y;
				allShapes[csize]= new RectangleS(color,2,temp1,temp2,fill);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				click_screen=false;
				choice=-1;
				color=BLACK;
				fill=WHITE;
				DisplayMenu();
			}
			if (choice==3 && click_screen==true)
			{
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				temp.push_back(temp1);
				for (int i=1;i<3;i++)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
					while(mouse_x>=-665 && mouse_x<=-632)
					{
						event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
					}
					temp1.x=mouse_x;
					temp1.y=mouse_y;
					temp.push_back(temp1);
				}
				allShapes[csize]= new Triangle(color,temp,BLACK);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);

				while(temp.size()!=0)
				{
					temp.pop_back();
				}
				click_screen=false;
				choice=-1;
				color=BLACK;
				fill=color;
				DisplayMenu();
			}
			if (choice==4 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp2.x=mouse_x;
				temp2.y=mouse_y;
				allShapes[csize]= new Circle(color,temp1,temp2);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				click_screen=false;
				choice=-1;
				color=BLACK;
				DisplayMenu();
			}
			if (choice==5 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				temp.push_back(temp1);
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(event!=5)
				{
					while(mouse_x>=-665 && mouse_x<=-632)
					{
						event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
					}
					temp1.x=mouse_x;
					temp1.y=mouse_y;
					temp.push_back(temp1);
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				allShapes[csize]= new PolygonS(color,temp.size(), temp, BLACK);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				while(temp.size()!=0)
				{
					temp.pop_back();
				}
				click_screen=false;
				choice=-1;
				color=BLACK;
				fill=color;
				DisplayMenu();
			}
			if (choice==6 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				temp.push_back(temp1);
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(event!=5)
				{
					while(mouse_x>=-665 && mouse_x<=-632)
					{
						event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
					}
					temp1.x=mouse_x;
					temp1.y=mouse_y;
					temp.push_back(temp1);
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				allShapes[csize]= new Curve(color,temp.size(),style,temp);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				while(temp.size()!=0)
				{
					temp.pop_back();
				}
				click_screen=false;
				choice=-1;
				color=BLACK;
				style=true;
				DisplayMenu();
			}
			if (choice==7 && click_screen==true)
			{
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				int count=0;
				char tempstr[100];
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				while(event!=2)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				while(key_pressed!=13)
				{
					tempstr[count]=key_pressed;
					count++;
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				tempstr[count]='\0';
				allShapes[csize]= new Text(color,count,temp1,tempstr);
				allShapes[csize]->draw();
				csize++;
				allShapes=extendBy1(allShapes,arrsize,csize);
				click_screen=false;
				choice=-1;
				color=BLACK;
				DisplayMenu();
			}
			while (choice==8 && click_screen==true)
			{
				DisplayMenu();
				lightUpButton(choice);
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				GP142_pixelXY(BLUE,mouse_x,mouse_y);
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				bool found=false;
				int index=-1;
				for (int i=csize-1;i>=0;i--)
				{
					if (allShapes[i]->contains(temp1)==true)
					{
						index=i;
						found=true;
						break;
					}
				}
				if(found==true)
				{
					allShapes=shrinkBy1(allShapes,arrsize,index);
					csize--;
					allShapes=sizecorrect(allShapes,arrsize,csize);
					for (int j=-304;j<307;j++)
					{
						GP142_lineXY(WHITE,-625,j,662,j,3);
					}
					for (int i=0;i<csize;i++)
					{
						allShapes[i]->draw();
					}
				}
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				if ((mouse_x>=-665 && mouse_x<=-632) && (mouse_y<=start_y && mouse_y>=start_y-352))
				{
					if (temp_y<start_y-251 && temp_y>=start_y-280)
					{
						choice=-1;
						DisplayMenu();
						click_screen=false;
						DisplayMenu();
					}
				}
				color=BLACK;
				index=-1;
				found=false;
			}
			while (choice==9 && click_screen==true)
			{
				DisplayMenu();
				lightUpButton(choice);
				while(mouse_x>=-665 && mouse_x<=-632)
				{
					event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				}
				temp1.x=mouse_x;
				temp1.y=mouse_y;
				bool found=false;
				int index=-1;
				for (int i=csize-1;i>=0;i--)
				{
					if (allShapes[i]->contains(temp1)==true)
					{
						index=i;
						found=true;
						break;
					}
				}
				if(found==true)
				{
					allShapes[index]->fill(color);
				}
				event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
				if ((mouse_x>=-665 && mouse_x<=-632) && (mouse_y<=start_y && mouse_y>=start_y-352))
				{
					if (temp_y<start_y-287 && temp_y>=start_y-316)
					{
						choice=-1;
						DisplayMenu();
						click_screen=false;
						DisplayMenu();
						color=BLACK;
					}
				}
				index=-1;
				found=false;
			}
			if (choice==10)
			{
				ofstream savetofile("drawing.pb");
				savetofile<<csize<<endl;
				for (int i=0;i<csize;i++)
				{
					allShapes[i]->save(savetofile);
				}
				savetofile.close();
				choice=-1;
				color=BLACK;
				DisplayMenu();
			}
			break;

		case GP142_KBD:
		default:
			//demo_handle_kbd(key_pressed);
			//your code against key pressed event goes here
			//while(mouse_x>=-665 && mouse_x<=-632)
			//{
			//	event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			//}
			//temp1.x=mouse_x;
			//temp1.y=mouse_y;
			//temp.push_back(temp1);
			//event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			///*while(event!=5)
			//{
			//	while(mouse_x>=-665 && mouse_x<=-632)
			//	{
			//		event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			//	}
			//	temp1.x=mouse_x;
			//	temp1.y=mouse_y;
			//	temp.push_back(temp1);
			//	event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			//}*/
			//GP142_rectangleXY(RED,temp1.x,temp1.y,mouse_x,mouse_y,2);

			/*a.draw();
			event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			temp1.x=mouse_x;
			temp1.y=mouse_y;
			bool qwerty=a.contains(temp1);
			event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			Point x;
			x.x=mouse_x;
			x.y=mouse_y;*/
			/*if (a.contains(x)==true)
			{
			a.changeColor(RED);
			}*/

			/*event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			temp1.x=mouse_x;
			temp1.y=mouse_y;
			event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			temp2.x=mouse_x;
			temp2.y=mouse_y;
			GP142_circleXY(color,temp1.x,temp1.y,40);*/
			/*ofstream linetest("drawing.pb");
			if (linetest.is_open())
			{
			linetest << a;
			linetest.close();
			}*/
			/*event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			int j=GP142_GetPixel(temp1.x,temp1.y);*/
			/*event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			temp1.x=mouse_x;
			temp1.y=mouse_y;
			temp.push_back(temp1);
			while(event!=5)
			{
			event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			temp1.x=mouse_x;
			temp1.y=mouse_y;
			temp.push_back(temp1);
			};
			PolygonS a(1,temp.size(), temp, 2);
			a.draw();*/
			/*event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			Point temp3;
			temp3.x=mouse_x;
			temp3.y=mouse_y;
			event = GP142_await_event(&mouse_x, &mouse_y, &key_pressed);
			Point temp4;
			temp4.x=mouse_x;
			temp4.y=mouse_y;
			RectangleS p(BLUE, 2, temp3, temp4, BLACK);
			p.draw();
			p.fill(RED);*/
			break;



			/* Ignore all other events, such as keystrokes, mouse clicks, */
			/* and periodic events.                                       */
			break;
		}  

	}  /* end event loop */


	/* Close the graphics window and exit */
	GP142_close();
	for (int i=0;i<csize;i++)
	{
		delete allShapes [i];
	}
	delete [] allShapes;
	return 0;

}