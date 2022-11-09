#include <iostream>

#include "List.h"
#include "Tree.h"

using IntList = List<int>;

class Solution {
public:
    bool isPalindrome(ListNode<int>* head) {
        std::vector<ListNode<int>*> nodes;
        while (head != nullptr) {
            nodes.push_back(head);
            head = head->next;
        }

        int left = 0, right = nodes.size() - 1;
        while (left < right) {
            if (nodes[left]->val != nodes[right]->val) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
};

int main() {
	Solution solution;
	//Tree tree("[3,9,20,null,null,15,7]");
	//std::cout << solution.maxDepth(tree.root);
	IntList list("[1,2,2,1]");
    std::cout << solution.isPalindrome(list.head);

	/*List<int> list({ 1,2,3,4 });

	list.print();*/

	/*Tree tree("");
	tree.serialize(tree.root);*/
	
	return 0;
}