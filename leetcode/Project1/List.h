#pragma once

#include <iostream>
#include <vector>
#include <string>

#include "ListNode.h"

template <typename T>
class List {
public:
	ListNode<T>* head;
public:
	List(std::vector<T>&& nums) {
		ListNode<T>* header = new ListNode<T>();
		ListNode<T>* node = header;
		for (auto num : nums) {
			node->next = new ListNode<T>(num);
			node = node->next;
		}

		head = header->next;
		delete header;
	}

	List(std::string data) {
		data.erase(0, 1);
		data.pop_back();

		ListNode<T>* header = new ListNode<T>();
		ListNode<T>* node = header;
		std::string str;
		std::stringstream ss(data);
		while (std::getline(ss, str, ',')) {
			int num = std::stoi(str);
			node->next = new ListNode<T>(num);
			node = node->next;
		}

		head = header->next;
		delete header;
	}

	void print() {
		ListNode<T>* node = head;
		while (node != nullptr) {
			std::cout << node->val << " ";
			node = node->next;
		}
	}
};