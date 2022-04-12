#include <iostream>

#include <limits.h>

using namespace std;

void merge(int arr[], int l, int q, int r)
{
    int i, j, k;
    int n1 = q - l + 1;
    int n2 =  r - q;

    int L[n1+1], R[n2+1];

    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[q + 1+ j];

    L[i] = INT_MAX;
    R[j] = INT_MAX;

    i = 0, j = 0, k = l;
    while(k <= r) {
        if(L[i] <= R[j])
            arr[k] = L[i++];
        else if(R[j] < L[i])
            arr[k] = R[j++];
        k++;
    }
}

/*l - left , r - right, q - mid */
void mergeSort(int n[], int l, int r)
{
    if (l < r)
    {
        int q = (r+l)/2;
        mergeSort(n, l, q);
        mergeSort(n, q+1, r);
        merge(n, l, q, r);
    }
}

int main()
{
    int n[] = {5,4,3,2,1};
    mergeSort(n, 0, (sizeof(n)/sizeof(int) - 1));

    for(auto i = 0; i < sizeof(n)/sizeof(int); i++) cout << n[i] << " ";
}
