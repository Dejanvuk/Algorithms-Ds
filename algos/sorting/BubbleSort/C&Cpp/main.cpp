#include <iostream>

using namespace std;

void swap(int* where) {
    int temp = where[1];
    where[1] = where[0];
    where[0] = temp;
}

void bubbleSort(int arr[], int n) {
    bool swapped;
    for(int i = 0; i < n - 1; i++) {
        swapped = false;
        for(int j = 0; j < n - 1 - i; j++) {
            if(arr[j] > arr[j+1]) {
                swap(&arr[j]);
                swapped = true;
            }
        }
        if(!swapped) return;
    }
}

int main()
{
    int arr[] = {5,4,3,2,1};
    bubbleSort(arr, sizeof(arr)/sizeof(int));

    for(int i = 0; i < sizeof(arr)/sizeof(int); i++) cout << arr[i];
}
