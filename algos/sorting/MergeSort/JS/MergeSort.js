function merge(n1,n2) {
    let array = [], i = 0,j = 0;
    n1.push(Number.MAX_VALUE);
    n2.push(Number.MAX_VALUE);

    while(array.length < (n1.length + n2.length - 2)) {
        if(n1[i] <= n2[j]) 
            array.push(n1[i++]);
        else if (n2[j] < n1[i])
            array.push(n2[j++]);
    }
    return array;

}

function mergeSort(n) {
    let l = n.length;
    if(l === 1) return n;
    else {
        return merge(mergeSort(n.slice(0,Math.ceil(l/2))),
                     mergeSort(n.slice(Math.ceil(l/2),l)));
    } 
}

console.log(mergeSort([99,2,5,1,1,2,9,7,4,1,44,1,3,4,0,4]));  