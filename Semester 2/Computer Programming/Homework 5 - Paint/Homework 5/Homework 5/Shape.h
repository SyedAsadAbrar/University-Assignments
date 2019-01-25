#include<iostream>
#include"GP142.h"
#include"GP142LIB.H"
#include<vector>
using namespace std;
struct Point
{
	double x, y;	//x,y coordinates of point
};
void DisplayMenu();
void Pixel(int color, int x, int y, int thickness);
void DrawLine(int color, const Point & p1, const Point & p2, int thickness, bool style);
bool PointLiesOnLine(const Point & a, const Point & b, const Point & c);
void lightUpButton(int choice);
void loadMenu();

class Shape
{
protected:
	vector <Point> p;		//array of points which are vertices of shape
	int color;		//indicating the boundary color or text color in case of text
	int n;	//number of points, length of string in case of text

public:
	void virtual draw() = 0;
	void virtual save(ofstream & output)=0;
	void virtual load(ifstream & input)=0;
	bool virtual contains(Point q) = 0;
};
class Text:public Shape
{
	char str[100];
public:
	Text();
	Text(int col, int num, const Point & start, char text[]);
	void changeColor(int col);
	void save(ofstream & output);
	void load(ifstream & input);
	void draw();
	bool contains(Point q);
};
class OpenShapes:public Shape
{
protected:
	bool style;

public:
	OpenShapes();
	OpenShapes(int col, int num, bool solid);
	void virtual changeColor(int color)=0;
};
class Line:public OpenShapes
{
public:
	Line();
	Line(int col, int num, bool solid, const Point & first, const Point & second);
	void draw();
	void load(ifstream & input);
	void save(ofstream & output);
	bool contains(Point q);
	void changeColor(int col);
};
class Curve:public OpenShapes
{
public:
	Curve();
	Curve(int col,int num, bool solid, const vector <Point> & temp);
	void draw();
	void save(ofstream & output);
	bool contains(Point q);
	void load(ifstream & input);
	void changeColor(int col);
};
class PolygonS:public Shape
{
protected:
	int fillColor;

public:
	PolygonS();
	PolygonS(int col, int num, const vector <Point> & temp, int fill);
	void draw();
	void save(ofstream & output);
	void fill(int color);
	bool contains(Point q);
	void load(ifstream & input);
};
class Triangle:public PolygonS
{
protected:
	int fillColor;

public:
	Triangle();
	Triangle(int col, const vector <Point> & temp, int fill);
	void draw();
	void save(ofstream & output);
	void fill(int color);
	bool contains(Point q);
	void load(ifstream & input);
};
class RectangleS:public PolygonS
{
public:
	RectangleS();
	RectangleS(int col, int num, Point & first, Point & second, int fill);
	void draw();
	void save(ofstream & output);
	void fill(int color);
	bool contains(Point q);
	void load(ifstream & input);
};
class Circle:public Shape
{
	double radius;
public:
	Circle();
	Circle(int col, Point center, Point edge);
	void draw();
	void save(ofstream & output);
	void fill(int color);
	bool contains(Point q);
	void load(ifstream & input);
};