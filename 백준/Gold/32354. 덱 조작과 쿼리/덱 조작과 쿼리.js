const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

class Node {
    constructor(sum, next) {
        this.sum = sum;
        this.next = next;
    }
}

const n = parseInt(input[0]);
const nodes = new Array(n + 1);
nodes[0] = new Node(0n, null);
const output = [];

for (let i = 1; i <= n; i++) {
    const tokens = input[i].trim().split(' ');
    const cmd = tokens[0];
    switch (cmd.length) {
        case 4: // PUSH
            {
                const val = BigInt(tokens[1]);
                nodes[i] = new Node(nodes[i - 1].sum + val, nodes[i - 1]);
            }
            break;
        case 3: // POP
            nodes[i] = nodes[i - 1].next;
            break;
        case 7: // RESTORE
            {
                const t = parseInt(tokens[1]);
                nodes[i] = nodes[t];
            }
            break;
        case 5: // PRINT
            nodes[i] = nodes[i - 1];
            output.push(nodes[i].sum.toString());
            break;
    }
}

console.log(output.join('\n'));
