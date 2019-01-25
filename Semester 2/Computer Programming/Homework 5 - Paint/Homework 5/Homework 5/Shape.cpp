#include"Shape.h"
#include"GP142.h"
#include<iostream>
#include<fstream>
#include<math.h>
#include<conio.h>
#include <string>
void lightUpButton(int choice)
{
	choice--;
	Point extreme_left;
	extreme_left.x=-675;
	extreme_left.y=325;

	Point start;
	start.x=240;
	start.y=305;

	int j=36*choice;

	GP142_lineXY(GOLD, extreme_left.x + 10 , start.y - (j), extreme_left.x + 10, start.y - (j+33), 2);
	GP142_lineXY(GOLD, extreme_left.x + 43 , start.y - (j), extreme_left.x + 43, start.y - (j+33), 2);
	GP142_lineXY(GOLD, extreme_left.x + 10 , start.y - (j), extreme_left.x + 43, start.y - (j), 2);
	GP142_lineXY(GOLD, extreme_left.x + 10 , start.y - (33+j), extreme_left.x + 43, start.y - (33+j), 2);
}
void DisplayMenu()
{
	Point extreme_left;
	extreme_left.x=-675;
	extreme_left.y=325;

	Point start;
	start.x=240;
	start.y=305;

	int j=0;

	for(int i=0;i<17;i++)
	{
		//vertical borders
		GP142_lineXY(NAVY_BLUE, extreme_left.x + 10 , start.y - (j), extreme_left.x + 10, start.y - (j+33), 2);
		GP142_lineXY(NAVY_BLUE, extreme_left.x + 43 , start.y - (j), extreme_left.x + 43, start.y - (j+33), 2);

		//horizontal borders
		GP142_lineXY(NAVY_BLUE, extreme_left.x + 10 , start.y - (j), extreme_left.x + 43, start.y - (j), 2);
		GP142_lineXY(NAVY_BLUE, extreme_left.x + 10 , start.y - (33+j), extreme_left.x + 43, start.y - (33+j), 2);

		j=j+36;
	}

	//Buttons

	//solid line
	GP142_lineXY(WHITE, extreme_left.x + 15, start.y - 8, extreme_left.x + 38, start.y - 8, 2);
	GP142_lineXY(WHITE, extreme_left.x + 38, start.y - 8, extreme_left.x + 15, start.y - 8, 2);

	//division between solid and dotted line
	GP142_lineXY(NAVY_BLUE, extreme_left.x + 10 , start.y - 16, extreme_left.x + 43, start.y - 16, 2);

	//dotted line
	Point one;
	one.x=extreme_left.x + 15;
	one.y=start.y - 24;

	Point two;
	two.x=extreme_left.x + 38;
	two.y=start.y - 24;

	Line dot(1,2,false,one,two);
	dot.draw();

	//rectangle
	GP142_lineXY(WHITE,extreme_left.x + 16,start.y-44,extreme_left.x + 37,start.y-44,2);
	GP142_lineXY(WHITE,extreme_left.x + 37,start.y-44,extreme_left.x + 16,start.y-44,2);
	GP142_lineXY(WHITE,extreme_left.x + 16,start.y-44,extreme_left.x + 16,start.y-61,2);
	GP142_lineXY(WHITE,extreme_left.x + 37,start.y-44,extreme_left.x + 37,start.y-61,2);
	GP142_lineXY(WHITE,extreme_left.x + 16,start.y-61,extreme_left.x + 37,start.y-61,2);

	//triangle
	GP142_lineXY(WHITE,extreme_left.x + 26,start.y-78,extreme_left.x + 37,start.y-97,2);
	GP142_lineXY(WHITE,extreme_left.x + 26,start.y-78,extreme_left.x + 17,start.y-97,2);
	GP142_lineXY(WHITE,extreme_left.x + 37,start.y-97,extreme_left.x + 17,start.y-97,2);
	
	//circle
	GP142_circleXY(WHITE,extreme_left.x+26,start.y-124,11);

	//polygon
	GP142_lineXY(WHITE,extreme_left.x + 34,start.y-150,extreme_left.x + 37,start.y-170,2);
	GP142_lineXY(WHITE,extreme_left.x + 20,start.y-155,extreme_left.x + 17,start.y-170,2);
	GP142_lineXY(WHITE,extreme_left.x + 37,start.y-170,extreme_left.x + 17,start.y-170,2);
	GP142_lineXY(WHITE,extreme_left.x + 20,start.y-155,extreme_left.x + 34,start.y-150,2);

	//Solid curve
	GP142_lineXY(WHITE,extreme_left.x + 25,start.y-190,extreme_left.x + 37,start.y-186,2);
	GP142_lineXY(WHITE,extreme_left.x + 25,start.y-190,extreme_left.x + 16,start.y-190,2);
	
	//Division b/w solid curve and dotted curve
	GP142_lineXY(NAVY_BLUE, extreme_left.x + 10 , start.y - 196, extreme_left.x + 43, start.y - 196, 2);

	//Dotted curve
	Point three;
	three.x=extreme_left.x + 25;
	three.y=start.y-206;

	Point four;
	four.x=extreme_left.x + 37;
	four.y=start.y-202;

	Point five;
	five.x=extreme_left.x + 16;
	five.y=start.y-206;

	vector <Point> curve_dot;
	curve_dot.push_back(five);
	curve_dot.push_back(three);
	curve_dot.push_back(four);

	Curve dotC(1, 3,false,curve_dot);
	dotC.draw();

	GP142_printfXY(WHITE, extreme_left.x + 17 , start.y - 251, 32, "T");

	GP142_printfXY(WHITE, extreme_left.x + 16 , start.y - 287, 32, "E");

	GP142_printfXY(WHITE, extreme_left.x + 16 , start.y - 323, 32, "B");

	GP142_printfXY(WHITE, extreme_left.x + 16 , start.y - 359, 32, "S");

	for (int j=start.y-361;j>start.y-392;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(RED,i,j);
		}
	}

	for (int j=start.y-397;j>start.y-428;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(GREEN ,i,j);
		}
	}

	for (int j=start.y-397-36;j>start.y-428-36;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(BLUE,i,j);
		}
	}

	for (int j=start.y-397-36-36;j>start.y-428-36-36;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(YELLOW,i,j);
		}
	}

	for (int j=start.y-397-72-36;j>start.y-428-72-36;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(ORANGE,i,j);
		}
	}

	for (int j=start.y-397-108-36;j>start.y-428-108-36;j--)
	{
		for (int i=extreme_left.x+11;i<extreme_left.x+42;i++)
		{
			GP142_pixelXY(WHITE,i,j);
		}
	}
};
void Pixel(int color, int x, int y, int thickness)
{
	GP142_lineXY(color,x,y,x,y,thickness);
};
void DrawLine(int color, const Point & p1, const Point & p2, int thickness, bool style)
{
	float difference_x, difference_y, increment, x, y;
	GP142_point first;
	first.x=p1.x;
	first.y=p1.y;
	GP142_point second;
	second.x=p2.x;
	second.y=p2.y;
	if (style==true)
	{
		GP142_lineP(color,first,second,thickness);
	}
	else
	{
		difference_y=abs(second.y-first.y);
		difference_x=abs(second.x-first.x);
		if (difference_x>=difference_y)
		{
			increment=difference_x;
		}
		else
		{
			increment=difference_y;
		}
		difference_x=(second.x-first.x)/increment;
		difference_y=(second.y-first.y)/increment;
		x=first.x+0.5;
		y=first.y+0.5;
		Pixel(color,first.x,first.y,2);
		Pixel(color,second.x,second.y,2);
		for (int i=0;i<=increment;i++)
		{
			if(i%9<6)
			{
				Pixel(color,x,y,2);
			}
			x=x+difference_x;
			y=y+difference_y;
		}
	}
};
bool PointLiesOnLine(const Point & a, const Point & b, const Point & c)
{
	float dy=b.y-a.y;
	float dx=b.x-a.x;
	float m=dy/dx;
	float m2=(c.y-a.y)/(c.x-a.x);
	if (m==m2)
	{
		return true;
	}
	else
	{
		return false;
	}
};
void loadMenu()
{
	Point p1;
	p1.x=-300;
	p1.y=0;
	char temp[100]="Would you like to load previously saved drawing?";
	Text a(WHITE,48,p1,temp);
	a.draw();
	p1.y=-100;
	p1.x=-200;
	char yes[100]="Yes";
	Text b(WHITE,3,p1,yes);
	b.draw();
	p1.y=-100;
	p1.x=45;
	char no[100]="No";
	Text c(WHITE,2,p1,no);
	c.draw();
	GP142_lineXY(RED,-210,-71,-160,-71,3);
	GP142_lineXY(RED,-210,-105,-160,-105,3);
	GP142_lineXY(RED,30,-71,80,-71,3);
	GP142_lineXY(RED,30,-105,80,-105,3);
	GP142_lineXY(RED,-210,-71,-210,-105,3);
	GP142_lineXY(RED,-160,-71,-160,-105,3);
	GP142_lineXY(RED,30,-71,30,-105,3);
	GP142_lineXY(RED,80,-71,80,-105,3);
}
//ostream & operator<<(ostream & output, const Line & L)
//{
//	output<<"Line"<<endl<<"Color->"<<L.color<<endl<<"Number of points->"<<L.n<<endl<<"Style->"<<L.style<<endl<<"First point->"<<endl<<L.p[0].x<<endl<<L.p[0].y<<endl;
//	output<<"Second point->"<<endl<<L.p[1].x<<endl<<L.p[1].y<<endl;
//	return output;
//}

//istream & operator>>(istream & input,Line & L)
//{
//	char buff[500];
//	input.getline(buff,500);
//	input.getline(buff,500);
//	char*token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	L.color=atoi(token);
//	input.getline(buff,500);
//	token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	L.n=atoi(token);
//	input.getline(buff,500);
//	token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	L.style=atoi(token);
//	Point temp1,temp2;
//	input.getline(buff,500);
//	input.getline(buff,500);
//	temp1.x=atoi(buff);
//	input.getline(buff,500);
//	temp1.y=atoi(buff);
//	input.getline(buff,500);
//	input.getline(buff,500);
//	temp2.x=atoi(buff);
//	input.getline(buff,500);
//	temp2.y=atoi(buff);
//	L.p.push_back(temp1);
//	L.p.push_back(temp2);
//	return input;
//}

//OpenShapes
OpenShapes::OpenShapes()
{
	color=0;
	n=0;
	style=true;	//default behavior
}
OpenShapes::OpenShapes(int col, int num, bool solid)
{
	color=col;
	n=num;
	style=solid;
};

//Line
Line::Line():OpenShapes()
{
}
Line::Line(int col, int num, bool solid, const Point & first, const Point & second):OpenShapes(col, num, solid)
{
	p.push_back(first);
	p.push_back(second);
}
void Line::draw()
{
	float difference_x, difference_y, increment, x, y;
	GP142_point first;
	first.x=p[0].x;
	first.y=p[0].y;
	GP142_point second;
	second.x=p[1].x;
	second.y=p[1].y;
	if (style==true)
	{
		GP142_lineP(color,first,second,2);
	}
	else
	{
		difference_x=abs(second.y-first.y);
		difference_y=abs(second.x-first.x);
		if (difference_x>=difference_y)
		{
			increment=difference_x;
		}
		else
		{
			increment=difference_y;
		}
		difference_x=(second.x-first.x)/increment;
		difference_y=(second.y-first.y)/increment;
		x=first.x+0.5;
		y=first.y+0.5;
		Pixel(color,first.x,first.y,2);
		Pixel(color,second.x,second.y,2);
		for (int i=1;i<=increment;i++)
		{
			if(i%9<6)
			{
				Pixel(color,x,y,2);
			}
			x=x+difference_x;
			y=y+difference_y;
		}
	}
}
void Line::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Line"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Style "<<endl<<style<<endl<<"First point "<<endl<<p[0].x<<endl<<p[0].y<<endl;
		output<<"Second point "<<endl<<p[1].x<<endl<<p[1].y<<endl;
	}
}
bool Line::contains(Point q)
{
	bool oncurve=false;
	double slope,c,qwe;
	slope=(p[0].y-p[1].y)/(p[0].x-p[1].x);
	c=p[0].y-(slope)*(p[0].x);
	qwe=((slope)*(q.x)+c);
	if(qwe-q.y<=1 && qwe-q.y>=-1)
	{
		oncurve=true;
	}
	if (oncurve==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}
void Line::changeColor(int col)
{
	color=col;
	draw();
}
void Line::load(ifstream & input)
{
	char buff[500];
	Point p1,p2;
	char*token;
	
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		style=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		p1.x=atoi(token);
		input.getline(buff,500);		
		token=strtok(buff," ");
		p1.y=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		p2.x=atoi(token);
		input.getline(buff,500);
		token=strtok(buff," ");
		p2.y=atoi(token);
		p.push_back(p1);
		p.push_back(p2);
	}
};
//void Line::load(const string & a)
//{
//	char buff[500];
//	ifstream input(a);
//	input.getline(buff,500);
//	input.getline(buff,500);
//	char*token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	color=atoi(token);
//	input.getline(buff,500);
//	token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	n=atoi(token);
//	input.getline(buff,500);
//	token=strtok(buff,"-");
//	token=strtok(NULL,">");
//	style=atoi(token);
//	Point temp1,temp2;
//	input.getline(buff,500);
//	input.getline(buff,500);
//	temp1.x=atoi(buff);
//	input.getline(buff,500);
//	temp1.y=atoi(buff);
//	input.getline(buff,500);
//	input.getline(buff,500);
//	temp2.x=atoi(buff);
//	input.getline(buff,500);
//	temp2.y=atoi(buff);
//	p.push_back(temp1);
//	p.push_back(temp2);
//};

//Curve
Curve::Curve():OpenShapes()
{
}
Curve::Curve(int col,int num, bool solid, const vector <Point> & temp):OpenShapes(col,num,solid)
{
	n=temp.size();
	for (int i=0;i<n;i++)
	{
		p.push_back(temp[i]);
	}
}
void Curve::draw()
{
	for (int i=0;i<n-1;i++)
	{
		DrawLine(color,p[i],p[i+1],2,style);
	}
}
void Curve::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Curve"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Style "<<endl<<style<<endl<<"Points"<<endl;
		for (int i=0;i<n;i++)
		{
			output<<p[i].x<<endl<<p[i].y<<endl;
		}
	}
}
bool Curve::contains(Point q)
{
	bool oncurve=false;
	double slope,c,qwe;
	for (int i=0;i<n-1;i++)
	{
		slope=(p[i].y-p[i+1].y)/(p[i].x-p[i+1].x);
		c=p[i].y-(slope)*(p[i].x);
		qwe=((slope)*(q.x)+c);
		if(qwe-q.y<=1 && qwe-q.y>=-1)
		{
			oncurve=true;
		}
	}
	if (oncurve==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}
void Curve::changeColor(int col)
{
	color=col;
	draw();
}
void Curve::load(ifstream & input)
{
	char buff[500];
	Point p1;
	vector <Point> temp;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		style=atoi(token);
		input.getline(buff,500);
		for (int i=0;i<n;i++)
		{
			input.getline(buff,500);
			p1.x=atoi(token);
			input.getline(buff,500);
			p1.y=atoi(token);
			temp.push_back(p1);
		}
	}
	p=temp;
};

//Polygon
PolygonS::PolygonS()
{
	color=0;
	n=0;
	fillColor=0;
}
PolygonS::PolygonS(int col, int num, const vector <Point> & temp, int fill)
{
	color=col;
	n=num;
	for (int i=0;i<n;i++)
	{
		p.push_back(temp[i]);
	}
	fillColor=fill;
}
void PolygonS::draw()
{
	for (int i=0;i<n-1;i++)
	{
		DrawLine(color,p[i],p[i+1],2,true);
	}
	DrawLine(color,p[n-1],p[0],2,true);
}
void PolygonS::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Polygon"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Points"<<endl;
		for (int i=0;i<n;i++)
		{
			output<<p[i].x<<endl<<p[i].y<<endl;
		}
		output<<"Fill Color "<<endl<<fillColor<<endl;
	}
}
bool PolygonS::contains(Point q)
{
	vector <double> y_vals;
	bool in_shape=false;
	double slope,c,qwe;
	for (int i=0;i<n;i++)
	{
		slope=(p[i].y-q.y)/(p[i].x-q.x);
		c=p[i].y-(slope)*(p[i].x);
		qwe=((slope)*(q.x)+c);
		y_vals.push_back(qwe);
	}
	for (int i=0;i<n;i++)
	{
		for (int j=0;j<n && i!=j;j++)
		{
			if (y_vals[i]-y_vals[j]<=1 && y_vals[i]-y_vals[i]>=-1)
			{
				in_shape=true;
			}
		}
	}
	if (in_shape==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}
void PolygonS::load(ifstream & input)
{
	char buff[500];
	Point p1;
	vector <Point> temp;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		for (int i=0;i<n;i++)
		{
			input.getline(buff,500);
			p1.x=atoi(token);
			input.getline(buff,500);
			p1.y=atoi(token);
			temp.push_back(p1);
		}
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		fillColor=atoi(token);
	}
	p=temp;
};

//Triangle
Triangle::Triangle()
{
	color=0;
	n=0;
	fillColor=0;
}
Triangle::Triangle(int col, const vector <Point> & temp, int fill)
{
	color=col;
	n=3;
	for (int i=0;i<n;i++)
	{
		p.push_back(temp[i]);
	}
	fillColor=fill;
}
void Triangle::draw()
{
	for (int i=0;i<n-1;i++)
	{
		DrawLine(color,p[i],p[i+1],2,true);
	}
	DrawLine(color,p[n-1],p[0],2,true);
}
void Triangle::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Triangle"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Points"<<endl;
		for (int i=0;i<n;i++)
		{
			output<<p[i].x<<endl<<p[i].y<<endl;
		}
		output<<"Fill Color "<<endl<<fillColor<<endl;
	}
}
bool Triangle::contains(Point q)
{
	vector <double> y_vals;
	bool in_shape=false;
	double slope,c,qwe;
	for (int i=0;i<n;i++)
	{
		slope=(p[i].y-q.y)/(p[i].x-q.x);
		c=p[i].y-(slope)*(p[i].x);
		qwe=((slope)*(q.x)+c);
		y_vals.push_back(qwe);
	}
	for (int i=0;i<n;i++)
	{
		for (int j=0;j<n && i!=j;j++)
		{
			if (y_vals[i]-y_vals[j]<=1 && y_vals[i]-y_vals[i]>=-1)
			{
				in_shape=true;
			}
		}
	}
	if (in_shape==true)
	{
		return true;
	}
	else
	{
		return false;
	}
}
void Triangle::load(ifstream & input)
{
	char buff[500];
	Point p1;
	vector <Point> temp;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		for (int i=0;i<n;i++)
		{
			input.getline(buff,500);
			p1.x=atoi(token);
			input.getline(buff,500);
			p1.y=atoi(token);
			temp.push_back(p1);
		}
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		fillColor=atoi(token);
	}
	p=temp;
};

//Rectangle
RectangleS::RectangleS()
{
	color=0;
	n=0;
	fillColor=0;
}
RectangleS::RectangleS(int col, int num, Point & first, Point & second, int fill)
{
	color=col;
	n=num;
	p.push_back(first);
	p.push_back(second);
	fill=fillColor;
}
void RectangleS::draw()
{
	GP142_point first;
	first.x=p[0].x;
	first.y=p[0].y;

	GP142_point third;
	third.x=p[1].x;
	third.y=p[1].y;

	GP142_point second;
	second.x=third.x;
	second.y=first.y;

	GP142_point fourth;
	fourth.x=first.x;
	fourth.y=third.y;

	GP142_lineP(color,first,second,2);	
	GP142_lineP(color,first,second,2);
	GP142_lineP(color,second,third,2);	
	GP142_lineP(color,third,fourth,2);	
	GP142_lineP(color,fourth,first,2);	
}
void RectangleS::fill(int color)
{
	int x1,x2,y1,y2;
	if(p[0].x<p[1].x)
	{
		x2=p[1].x;
		x1=p[0].x;
	}
	else
	{
		x2=p[0].x;
		x1=p[1].x;	
	}
	if (p[0].y<p[1].y)
	{
		y2=p[1].y;
		y1=p[0].y;
	}
	else
	{
		y2=p[0].y;
		y1=p[1].y;
	}
	for (int j=y1+2;j<y2-1;j++)
	{
		for (int i=x1+2;i<x2-1;i++)
		{
			GP142_pixelXY(color,i,j);
		}
	}
}
void RectangleS::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Rectangle"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Points"<<endl;
		for (int i=0;i<n;i++)
		{
			output<<p[i].x<<endl<<p[i].y<<endl;
		}
		output<<"Fill Color "<<endl<<fillColor<<endl;
	}
}
bool RectangleS::contains(Point q)
{
	bool xtrue=false;
	if(p[0].x<p[1].x)
	{
		if (q.x>=p[0].x && q.x<=p[1].x)
		{
			xtrue=true;
		}
		else
		{
			xtrue=false;
		}
	}
	else if(p[0].x>p[1].x)
	{
		if (q.x>=p[1].x && q.x<=p[0].x)
		{
			xtrue=true;
		}
		else
		{
			xtrue=false;
		}
	}
	if(p[0].y<p[1].y)
	{
		if (q.y>=p[0].y && q.y<=p[1].y)
		{
			if (xtrue==true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	else if(p[0].y>p[1].y)
	{
		if (q.y>=p[1].y && q.y<=p[0].y)
		{
			if (xtrue==true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
void RectangleS::load(ifstream & input)
{
	char buff[500];
	Point p1;
	vector <Point> temp;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		for (int i=0;i<n;i++)
		{
			input.getline(buff,500);
			p1.x=atoi(token);
			input.getline(buff,500);
			p1.y=atoi(token);
			temp.push_back(p1);
		}
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		fillColor=atoi(token);
	}
	p=temp;
};

//circle
Circle::Circle()
{
	color=0;
	n=0;
	radius=0;
}
Circle::Circle(int col, Point center, Point edge)
{
	color=col;
	n=1;
	radius=sqrt((edge.x-center.x)*(edge.x-center.x)+(edge.y-center.y)*(edge.y-center.y));
	p.push_back(center);
	p.push_back(edge);
}
void Circle::draw()
{
	GP142_point a;
	a.x=p[0].x;
	a.y=p[0].y;
	GP142_circleP(color,a,radius);
}
void Circle::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Circle"<<endl<<"Color "<<endl<<color<<endl<<"Center"<<endl;
		for (int i=0;i<n;i++)
		{
			output<<p[i].x<<endl<<p[i].y<<endl;
		}
		output<<"Radius "<<endl<<radius<<endl;
	}
}
bool Circle::contains(Point q)
{
	if((q.x - p[0].x)*(q.x - p[0].x) + (q.y - p[0].y)*(q.y - p[0].y) < (radius*radius))
	{
		return true;
	}
	else
		return false;
};
void Circle::load(ifstream & input)
{
	char buff[500];
	Point p1;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		p1.x=atoi(token);
		input.getline(buff,500);
		p1.y=atoi(token);
		p.push_back(p1);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		radius=atoi(token);
	}
};

//Text
Text::Text()
{
	color=BLACK;
	n=0;
	str[0]=NULL;
}
Text::Text(int col, int num, const Point & start, char text[])
{
	color=col;
	n=num;
	p.push_back(start);
	for (int i=0;text[i]!='\0';i++)
	{
		str[i]=text[i];
	}
	str[n]='\0';
}
void Text::draw()
{
	int placement_x=p[0].x;
	int placement_y=p[0].y;
	for(int i=0;str[i]!='\0';i++)
	{
		GP142_printfXY(color,placement_x,placement_y,20,"%c",str[i]);
		placement_x=placement_x+11;
		if (str[i]=='m' || str[i]=='w' || str[i]=='W')
		{
			placement_x=placement_x+4;
		}
		if (str[i]=='i' || str[i]=='l' || str[i]=='l'|| str[i]=='r'|| str[i]=='j'|| str[i]=='t')
		{
			placement_x=placement_x-5;
		}
	}
}
void Text::save(ofstream & output)
{
	if (output.is_open())
	{
		output<<"Text"<<endl<<"Color "<<endl<<color<<endl<<"Number of points "<<endl<<n<<endl<<"Starting point"<<endl;
		output<<p[0].x<<endl<<p[0].y<<endl;
		output<<"String "<<endl<<str<<"\0"<<endl;
	}
}
bool Text::contains(Point test)
{
	bool x=false;
	if ((test.x>=p[0].x) && test.x<=(test.x+n*10/2))
	{
		x=true;
	}
	if (test.y>=p[0].y && test.y<=(p[0].y+10))
	{
		if (x==true)
		{
			return true;
		}
	}
	else
	{
		return false;
	}
}
void Text::changeColor(int col)
{
	col=color;
	draw();
}
void Text::load(ifstream & input)
{
	char buff[500];
	Point p1;
	char*token;
	if (input.is_open())
	{
		input.getline(buff,500);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		color=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		n=atoi(token);
		input.getline(buff,500);
		input.getline(buff,500);
		p1.x=atoi(token);
		input.getline(buff,500);
		p1.y=atoi(token);
		p.push_back(p1);
		input.getline(buff,500);
		input.getline(buff,500);
		token=strtok(buff," ");
		int count=0;
		for (;token[count]!='\0';count++)
		{
			str[count]=token[count];
		}
		str[count]='\0';
	}
};