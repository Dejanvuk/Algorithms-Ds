#include <iostream>

using namespace std;

void insertionSort(int arr[], int n) {
    for(int i = 1; i < n; i++) {
        int pos = i;
        for(int j = i - 1; j >= 0; j--) {
            if(arr[pos] < arr[j]) {
                int temp = arr[j];
                arr[j] = arr[pos];
                arr[pos] = temp;
                pos = j;
            }
        }
    }
}


int main()
{
    int arr[] = {44,3,1,6,9,4,5,2,0,1,2,2,3};
    insertionSort(arr, sizeof(arr)/sizeof(int));
    for(int i = 0; i < sizeof(arr)/sizeof(int); i++) cout << arr[i] << " ";
}
