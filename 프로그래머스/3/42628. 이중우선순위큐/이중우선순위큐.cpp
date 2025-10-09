#include <string>
#include <vector>
#include <queue>
#include <functional>
#include <sstream>
#include <climits>

using namespace std;

constexpr char INS = 'I';
constexpr int MAX = 1;

struct Node {
    bool del;
    int num;

    Node(int num) : del(false), num(num) {}
};

struct max_cmp {
    bool operator()(Node* a, Node* b) const {
        return a->num < b->num;
    }
};

struct min_cmp {
    bool operator()(Node* a, Node* b) const {
        return a->num > b->num;
    }
};

vector<int> solution(vector<string> operations) {
    char op;
    int i;
    int len;
    int num;
    Node* node;
    Node* max_nil;
    Node* min_nil;
    vector<int> ans;
    priority_queue<Node*, vector<Node*>, max_cmp> max_pq;
    priority_queue<Node*, vector<Node*>, min_cmp> min_pq;
    stringstream ss;

    max_pq.emplace(max_nil = new Node(INT_MIN));
    min_pq.emplace(min_nil = new Node(INT_MAX));
    len = operations.size();
    for (i = 0; i < len; i++) {
        ss.clear();
        ss.str(operations[i]);
        ss >> op;
        ss >> num;
        if (op == INS) {
            node = new Node(num);
            min_pq.emplace(node);
            max_pq.emplace(node);
        } else {
            if (num == MAX) {
                while (max_pq.top()->del) {
                    max_pq.pop();
                }
                if (max_pq.top() != max_nil) {
                    max_pq.top()->del = true;
                    max_pq.pop();
                }
            } else {
                while (min_pq.top()->del) {
                    min_pq.pop();
                }
                if (min_pq.top() != min_nil) {
                    min_pq.top()->del = true;
                    min_pq.pop();
                }
            }
        }
    }
    while (max_pq.top()->del) {
        max_pq.pop();
    }
    if (max_pq.top() == max_nil) {
        ans.emplace_back(0);
        ans.emplace_back(0);
    } else {
        ans.emplace_back(max_pq.top()->num);
        while (min_pq.top()->del) {
            min_pq.pop();
        }
        ans.emplace_back(min_pq.top()->num);
    }
    return ans;
}