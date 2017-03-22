#include <stdio.h>

int concatnate(int x, int y){
	int pow = 10;
	while (y > pow)
	{
		pow *= 10;
	}
	return (x * pow + y);
}
int atoi(const char* s) {
	int isNegative = 0;
	if( *s == '-')
	{
		isNegative = 1;
		s++;
	}
	int result = 0;
	while( *s != '\0')
	{
		int i = (int)*s;
		result = concatnate(result, i - 48);
        s++;
	}
	if(isNegative)
	{
		return 0-result;
	}
	return result;
	
}

int main(void) {
	printf("%d\n", atoi(""));
	printf("%d\n", atoi("0"));
	printf("%d\n", atoi("0123"));
	printf("%d\n", atoi("1234"));
	printf("%d\n", atoi("-1234"));
	
	return 0;
}