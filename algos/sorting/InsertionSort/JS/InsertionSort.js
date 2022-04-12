function insertionSort(n) {
    if(n.length == 1) return n;
    
    let pos;
    
    for(let i = 1; i < n.length; i++) {
        pos = i;
        for(let j = i - 1; j >= 0; j--) {
            if(n[pos] < n[j]) {
                let temp = n[j];
                n[j] = n[pos];
                n[pos] = temp;
                pos = j;
            }
        }
    }

    return n;
}

console.log(insertionSort([5,4,3,2,1])); 