
void MergeStep(int a[],int r[],int s,int m,int n)
{
    int i,j,k;
    k=s;  // start1 ，
    i=s;  // start1
    j=m+1; // start2 ，
    
    //1   [(1,9),(5,7)]   len = 2  (1,9),(5,7)  r[0]=1 , r[1]= 5 ; r[2] =7 (while断开), r[3]=9

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
            MergePass(p,a,n,len);	// p到a ,
        }else{
            MergePass(a,p,n,len);	// a到p
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
#define ARRAYLEN 4	//需要排序的数据元素数量

//  s(start) ,m(第1序段end序号),n(end序号)
void MergeStep(int a[],int r[],int s,int m,int n) //相邻有序段合并
{
    int i,j,k;
    k=s;  // start1 ，第1段起点
    i=s;  // start1
    j=m+1; // start2 ，第2段起点
    
    // 至少把一个小序列元素全部比较完毕 ，剩下的一个序列是有序的
    while(i<=m && j<=n) //当两个有序表都未结束时，循环比较
    {
        //当较小的元素复制到R中 ,前面只取了1半的元素
        
        printf(" i=%d ",i);
        
        if(a[i]<=a[j]){    //  i <= j , 取i++
            r[k++]=a[i++];
        }
        else if (a[i]>a[j]){ // i > j , 取j++
            r[k++]=a[j++];
        }
        
    }
    
    while(i<=m) //将未合并的部分复制到R中
        r[k++]=a[i++];
    while(j<=n)
        r[k++]=a[j++]; //将未合并的部分复制到R中
    
    
    printf(" ");
    for (int i = 0 ; i < 4; i++) {
        printf(" %d ",r[i]);
    }
    
}

/*
 
 len = 1  i=0   1  9  2065190712  32767
 len = 1  i=2   1  9  5  7
 len = 2  i=0  i=1  i=1   1  5  7  9

    保证合并前最小序段是有序的。

0   [ 9,1],[5,7]    len = 1  [9,1]
1   [(1,9),(5,7)]   len = 2  (1,9),(5,7)  r[0]=1 , r[1]= 5 ; r[2] =7 (while断开), r[3]=9
 
 
 保证合并前最小序段()是有序的。
 
 0    [(69),(65)],[90,37],[92,6],[28,54],34    len = 1  [69,65]
 1    [(65,69),(37,90)],[(6,92),(28,54)],34    len = 2  (65,69),(37,90)  r[0]=37 , r[1]= 65 ; r[2] =69 , r[3]=90
 2    [(37,65,69,90 ),(6,28,54,92)],34         len = 4  (37,65,69,90 ),(6,28,54,92)
 3    [(6,28,37,54,65,69,90,92),34]
 4     (6,28,34,37,54,65,69,90,92)
 
 */



void MergePass(int a[],int r[],int n,int len)  //len为定值，完成一遍合并的函数
{
    int s,e;
    s = 0;  // 第1序段 start
    while(s+len < n) //至少有两个有序段
    {
        e= s+2*len-1; // 合并后的 end
        if(e >= n) { //最后一段可能少于len个结点
            e=n-1;
        }
        // s(start) ,s+len-1(第1序段end序号),e (合并后end)
        printf("\n len = %d ",len);
        MergeStep(a,r,s,s+len-1,e);  // 相邻有序段合并
        s = e+1; //下一对有序段中左段的开始下标
    }
    
    if(s<n){ //还剩一个有序段，将其从A中复制到R中
        for(;s<n;s++)
            r[s]=a[s];
    }
}

void MergeSort(int a[],int n)
{
    int *p;
    int len = 1;     //有序序列的长度
    int f=0;	   //变量f作标志
    if(!(p=(int *)malloc(sizeof(int)*n)))	//分配内存空间，保存临时数据 p
    {
    }
    while(len<n)  // (1)对len变化下合并
    {
        if(f){    // (2)交替地在A和P之间来回合并
            MergePass(p,a,n,len);	// 调用MergePass，对p合并到a ,第1个参数向第2个参数
        }else{
            MergePass(a,p,n,len);	// 调用MergePass，对a合并到p
        }
        len *= 2;	 // 增加有序序列长度
        f = 1-f;     // 使f值在0和1之间切换
    }
    
    if(f){	// 结果在p内
        for(f=0;f<n;f++) //将数组p中的数据复制到数组a
            a[f]=p[f];
    }
    free(p); // 释放分配的内存
}

int main()
{
    int i;
    
    //int a[]={2,3,49,5,17,2,3,5,0,1};
    
    int a[]={9,1,5,7};
    
    printf("原数据:"); 	//输出生成的随机数
    for(i=0;i<ARRAYLEN;i++)
        printf("%d ",a[i]);
    printf("\n");
    MergeSort(a,ARRAYLEN);	//调用合并排序函数
    printf("\n排序后:");
    for(i=0;i<ARRAYLEN;i++)	//输出排序后的结果
        printf("%d ",a[i]);
    printf("\n");

    return 0;
}


/*
 
 原数据:2 3 49 5 17 2 3 5 0 1
 排序后:0 1 2 2 3 3 5 5 17 49
 
 原数据:9 1 5 7 0 0 0 0 0 0
 排序后:0 0 0 0 0 0 1 5 7 9
 
 */
