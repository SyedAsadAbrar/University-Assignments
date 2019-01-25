#include <cstdlib>
#include <iostream>
#include <time.h>
#include <stdio.h>
using namespace std;

void Merge(long Array[], int start, int mid, int end){
    int s1=mid-start+1,s2=end-mid;
    long *A1=new long[s1],*A2= new long[s2];
    int it1=0,it2=0,k=start;
    for(int i=0;i<s1;i++){
        A1[i]=Array[i+start];
    }
    for(int j=0;j<s2;j++){
        A2[j]=Array[j+mid+1];
    }
    while(it1<s1 && it2<s2){
        if(A1[it1]<=A2[it2]){
            Array[k++]=A1[it1++];
        }
        else{
            Array[k++]=A2[it2++];
        }
    }
    while(it1<s1){
        Array[k++]=A1[it1++];
    }
    while(it2<s2){
        Array[k++]=A2[it2++];
    }
}
void MergeSort(long Array[], int start, int end){
    if(start<end){
        int mid=(start+end)/2;
        MergeSort(Array, start, mid);
        MergeSort(Array, mid+1, end);
        Merge(Array,start,mid,end);
    }
}
void MergeSort_Iterative(long Array[], int size){
    for(int i=1;i<size;i=i*2){
        for(int start=0;start<size-1;start=start+(2*i)){
            int mid=start+i-1;
            int end=min(start + (2*i) - 1, size-1);
            Merge(Array,start,mid,end);
        }
    }
}
void generate100(long Array[]){
    int start =0;
    for(int i=0; i< 10;i++){
        start = rand()% 4294967295 + 0;
        Array[i]=rand()% 4294967295 + start;
    }
}

void Checker(long a[],int size){
    for(int i=1;i<size;i++){
		//cout << a[i] <<  " " ;
        for(int j=i-1;j>0;j--){
            if (a[i]<a[j]){cout << "Issue Spotted" << endl; return;}
        }
    }
}
void print(long a[],int size){
    for(int i=0;i<size;i++){
		cout << a[i] <<  " " ;
    }
	cout << endl;
}

int main() {
	long T1[10];
    generate100(T1);

	//Checker(T1,100);
    int start_time = clock();
   MergeSort_Iterative(T1,10);
    int end_time = clock();
	Checker(T1,10);
    int diff = ((end_time - start_time)*1000)/CLOCKS_PER_SEC;   
    cout <<endl<< "Time Taken QuickSortD: "<<  " "<< diff << endl;
	print(T1,10);
    return 0;
}
