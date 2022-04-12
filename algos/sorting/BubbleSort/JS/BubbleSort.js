function bubbleSort(n) {
    let donePass = false;
    let j = 0;
    
    while(true) {
        for(let i = 0; i < n.length - 1 - j; i++) {
            if(n[i] > n[i+1]) {
                let temp = n[i+1];
                n[i+1]  = n[i];
                n[i] = temp;
                donePass = true;
            }
        }
        if(donePass == false) return n;
        else {
            j++;
            donePass = false;
        }
    }
}