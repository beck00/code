
void MergeStep(int a[],int r[],int s,int m,int n)
{
    int i,j,k;
    k=s;  // start1 ��
    i=s;  // start1
    j=m+1; // start2 ��
    
    //1   [(1,9),(5,7)]   len = 2  (1,9),(5,7)  r[0]=1 , r[1]= 5 ; r[2] =7 (while�Ͽ�), r[3]=9

    while(i<=m && j<=n)
    {
        printf(" i=%d ",i);
        
        if(a[i]<=a[j]){
            r[k++]=a[i++];
        }
        else if (a[i]>a[j]){
            r[k++]=a[j++];
        }
    }
    
    while(i<=m)
        r[k++]=a[i++];
    while(j<=n)
        r[k++]=a[j++];
    
    printf(" ");
    for (int i = 0 ; i < 4; i++) {
        printf(" %d ",r[i]);
    }
    
}




void MergePass(int a[],int r[],int n,int len)
{
    int s,e;
    s = 0;
    while(s+len < n)
    {
        e= s+2*len-1;
        if(e >= n) {
            e=n-1;
        }
        printf("\n len = %d ",len);
        MergeStep(a,r,s,s+len-1,e);
        s = e+1;
    }
    
    if(s<n){
        for(;s<n;s++)
            r[s]=a[s];
    }
}


void MergeSort(int a[],int n)
{
    int *p;
    int len = 1;
    int f=0;
    if(!(p=(int *)malloc(sizeof(int)*n)))
    {
    }
    while(len<n)
    {
        if(f){
            MergePass(p,a,n,len);	// p��a ,
        }else{
            MergePass(a,p,n,len);	// a��p
        }
        len *= 2;
        f = 1-f;
    }
    
    if(f){
        for(f=0;f<n;f++)
            a[f]=p[f];
    }
    free(p);
}


int mainkk()
{
    int i;
    int a[]={9,1,5,7};
    
   
    for(i=0;i<ARRAYLEN;i++)
        printf("%d ",a[i]);
    printf("\n");
    MergeSort(a,ARRAYLEN);
   
    for(i=0;i<ARRAYLEN;i++)
        printf("%d ",a[i]);
    printf("\n");
    
    return 0;
}


-------------------------------------------------------------------
#include <stdio.h>
#include <stdlib.h>
#define ARRAYLEN 4	//��Ҫ���������Ԫ������

//  s(start) ,m(��1���end���),n(end���)
void MergeStep(int a[],int r[],int s,int m,int n) //��������κϲ�
{
    int i,j,k;
    k=s;  // start1 ����1�����
    i=s;  // start1
    j=m+1; // start2 ����2�����
    
    // ���ٰ�һ��С����Ԫ��ȫ���Ƚ���� ��ʣ�µ�һ�������������
    while(i<=m && j<=n) //�����������δ����ʱ��ѭ���Ƚ�
    {
        //����С��Ԫ�ظ��Ƶ�R�� ,ǰ��ֻȡ��1���Ԫ��
        
        printf(" i=%d ",i);
        
        if(a[i]<=a[j]){    //  i <= j , ȡi++
            r[k++]=a[i++];
        }
        else if (a[i]>a[j]){ // i > j , ȡj++
            r[k++]=a[j++];
        }
        
    }
    
    while(i<=m) //��δ�ϲ��Ĳ��ָ��Ƶ�R��
        r[k++]=a[i++];
    while(j<=n)
        r[k++]=a[j++]; //��δ�ϲ��Ĳ��ָ��Ƶ�R��
    
    
    printf(" ");
    for (int i = 0 ; i < 4; i++) {
        printf(" %d ",r[i]);
    }
    
}

/*
 
 len = 1  i=0   1  9  2065190712  32767
 len = 1  i=2   1  9  5  7
 len = 2  i=0  i=1  i=1   1  5  7  9

    ��֤�ϲ�ǰ��С���������ġ�

0   [ 9,1],[5,7]    len = 1  [9,1]
1   [(1,9),(5,7)]   len = 2  (1,9),(5,7)  r[0]=1 , r[1]= 5 ; r[2] =7 (while�Ͽ�), r[3]=9
 
 
 ��֤�ϲ�ǰ��С���()������ġ�
 
 0    [(69),(65)],[90,37],[92,6],[28,54],34    len = 1  [69,65]
 1    [(65,69),(37,90)],[(6,92),(28,54)],34    len = 2  (65,69),(37,90)  r[0]=37 , r[1]= 65 ; r[2] =69 , r[3]=90
 2    [(37,65,69,90 ),(6,28,54,92)],34         len = 4  (37,65,69,90 ),(6,28,54,92)
 3    [(6,28,37,54,65,69,90,92),34]
 4     (6,28,34,37,54,65,69,90,92)
 
 */



void MergePass(int a[],int r[],int n,int len)  //lenΪ��ֵ�����һ��ϲ��ĺ���
{
    int s,e;
    s = 0;  // ��1��� start
    while(s+len < n) //���������������
    {
        e= s+2*len-1; // �ϲ���� end
        if(e >= n) { //���һ�ο�������len�����
            e=n-1;
        }
        // s(start) ,s+len-1(��1���end���),e (�ϲ���end)
        printf("\n len = %d ",len);
        MergeStep(a,r,s,s+len-1,e);  // ��������κϲ�
        s = e+1; //��һ�����������εĿ�ʼ�±�
    }
    
    if(s<n){ //��ʣһ������Σ������A�и��Ƶ�R��
        for(;s<n;s++)
            r[s]=a[s];
    }
}

void MergeSort(int a[],int n)
{
    int *p;
    int len = 1;     //�������еĳ���
    int f=0;	   //����f����־
    if(!(p=(int *)malloc(sizeof(int)*n)))	//�����ڴ�ռ䣬������ʱ���� p
    {
    }
    while(len<n)  // (1)��len�仯�ºϲ�
    {
        if(f){    // (2)�������A��P֮�����غϲ�
            MergePass(p,a,n,len);	// ����MergePass����p�ϲ���a ,��1���������2������
        }else{
            MergePass(a,p,n,len);	// ����MergePass����a�ϲ���p
        }
        len *= 2;	 // �����������г���
        f = 1-f;     // ʹfֵ��0��1֮���л�
    }
    
    if(f){	// �����p��
        for(f=0;f<n;f++) //������p�е����ݸ��Ƶ�����a
            a[f]=p[f];
    }
    free(p); // �ͷŷ�����ڴ�
}

int main()
{
    int i;
    
    //int a[]={2,3,49,5,17,2,3,5,0,1};
    
    int a[]={9,1,5,7};
    
    printf("ԭ����:"); 	//������ɵ������
    for(i=0;i<ARRAYLEN;i++)
        printf("%d ",a[i]);
    printf("\n");
    MergeSort(a,ARRAYLEN);	//���úϲ�������
    printf("\n�����:");
    for(i=0;i<ARRAYLEN;i++)	//��������Ľ��
        printf("%d ",a[i]);
    printf("\n");

    return 0;
}


/*
 
 ԭ����:2 3 49 5 17 2 3 5 0 1
 �����:0 1 2 2 3 3 5 5 17 49
 
 ԭ����:9 1 5 7 0 0 0 0 0 0
 �����:0 0 0 0 0 0 1 5 7 9
 
 */
