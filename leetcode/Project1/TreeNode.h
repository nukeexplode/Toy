#pragma once

#include <iostream>

class TreeNode {
public:
	int val;
	TreeNode* left;
	TreeNode* right;

public:
	TreeNode(int _val) : val(_val), left(nullptr), right(nullptr) {

	}
};