function selectionSort(n) {
    if(n.length == 1) return n;
    
    for(let i = 0; i < n.length - 1; i++) {
        let min = i;
        for(let j = i + 1; j < n.length; j++) { 
            if(n[j] < n[min]) {
                min = j;
            }
        }
        let temp = n[i];
        n[i] = n[min];
        n[min] = temp;
    }

    return n;
}

console.log(selectionSort([64,25,12,22,11])); 