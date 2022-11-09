#pragma once
template <typename T>
class ListNode {
public:
	ListNode<T>* next;
	T val;
public:
	ListNode() : next(nullptr), val() {

	}

	ListNode(T _val) : val(_val) {

	}

	ListNode(T _val, ListNode* _next) {
		val = _val;
		next = _next;
	}
};