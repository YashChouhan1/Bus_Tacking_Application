#include<string.h>
using namespace std;
int main()
{
	char s[30];
	int i;
	cout<<"enter string = ";
	cin>>s;
	for(i=0;i<(strlen(s)-2);i++)
	{
		
	if(s[i] == '1' && s[i+1] == '1' && s[i+2] == '1' )
		
	{
		
	cout<<"String Accepted";
			
	break;	
		
	}
	}
	if(i == strlen(s)-2)
	{
		
	cout<<"String Rejected";
	}
	return(0);
	}
