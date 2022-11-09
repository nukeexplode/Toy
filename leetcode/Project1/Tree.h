#pragma once

#include <iostream>
#include <string>
#include <queue>
#include <sstream>
#include <utility>
#include <cassert>

#include "TreeNode.h"

//template <typename T>
//class Tree {
//public:
//	TreeNode<T>* root;
//public:
//    TreeNode<T>* deserialize(string data) {
//        if (data.empty) return nullptr;
//        std::stringstream ss(data);
//        std::queue<TreeNode*> q;
//        std::string str;
//        TreeNode* root = nullptr;
//
//        while (ss) {
//            ss >> str;
//            if (str == null) {
//                continue;
//            }
//
//            int val = std::stoi(str);
//            TreeNode* new_node = new TreeNode(val);
//            if (root == nullptr) {
//                root = new_node;
//                q.push(root);
//            }
//            else {
//                TreeNode* node = q.front();
//
//                if (node->left == nullptr) {
//                    node->left = new_node;
//                    q.push(node->left)
//                }
//                else {
//                    node->right = new_node;
//                    q.pop();
//                    q.push(node->right);
//                }
//            }
//        }
//
//        return root;
//    }
//};

class Tree {
private:
    enum STAT
    {
        HAVENOCHILD = 0,
        HAVELEFTCHILD,
        HAVERIGHTCHILD,
    };
public:
    TreeNode* root;
public:
    Tree() : root(nullptr) {

    }

    Tree(std::string data) : root(nullptr) {
        parse(data);
        if (data.empty()) {
            return;
        }
        std::stringstream ss(data);
        std::queue<std::pair<TreeNode*, STAT>> q;
        std::string str;

        while (std::getline(ss, str, ',')) {
            if (root != nullptr && q.empty()) {
                std::cout << "树节点没对上" << std::endl;
                std::cout << ss.str();
                break;
            }

            if (str == "null") {
                if (q.front().second == HAVENOCHILD) {
                    q.front().second = HAVELEFTCHILD;
                }
                else if (q.front().second == HAVELEFTCHILD) {
                    q.pop();
                }
                else {
                    q.pop();
                }

                continue;
            }

            int val = std::stoi(str);
            enum STAT stat = HAVENOCHILD;
            TreeNode* node = nullptr;
            TreeNode* new_node = new TreeNode(val);
            if (root == nullptr) {
                root = new_node;
                q.emplace(root, HAVENOCHILD);
            }
            else {
                node = q.front().first;
                stat = q.front().second;

                if (stat == HAVENOCHILD) {
                    assert(node->left == nullptr);
                    q.front().second = HAVELEFTCHILD;
                    node->left = new_node;
                    q.emplace(node->left, HAVENOCHILD);
                }
                else {
                    node->right = new_node;
                    q.front().second = HAVERIGHTCHILD;
                    q.pop();
                    q.emplace(node->right, HAVENOCHILD);
                }
            }
        }

        std::cout << serialize(root);
    }

    std::string serialize(TreeNode* root) {
        if (root == nullptr) return "";
        std::vector<std::string> strs;
        std::string res;
        std::queue<TreeNode*> q;
        q.push(root);
        strs.push_back(std::to_string(root->val));

        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();

            if (node->left) {
                q.push(node->left);
                strs.push_back(std::to_string(node->left->val));
            }
            else {
                strs.push_back("null");
            }

            if (node->right) {
                q.push(node->right);
                strs.push_back(std::to_string(node->right->val));
            }
            else {
                strs.push_back("null");
            }
        }

        while (strs.back() == "null") {
            strs.pop_back();
        }

        for (auto str : strs) {
            res += str + " ";
        }
        res.pop_back();
        return res;
    }

    void parse(std::string& data) {
        data.erase(0, 1);
        data.pop_back();
    }
};