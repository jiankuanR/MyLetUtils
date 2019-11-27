package com.example.demo;

import java.util.TreeMap;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: BinarySearchTree
 * @create 2019/9/16
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class BinarySearchTree {

	private int data;

	private BinarySearchTree leftTree;

	private BinarySearchTree rightTree;

	public BinarySearchTree(int data) {
		this.data = data;
		this.leftTree = null;
		this.rightTree = null;
	}

	public void insert(BinarySearchTree root,int data) {
		if (root.data >= data) {
			if (root.leftTree == null) {
				root.leftTree = new BinarySearchTree(data);
			} else {
				insert(root.leftTree,data);
			}
		} else {
			if (root.rightTree == null) {
				root.rightTree = new BinarySearchTree(data);
			} else {
				insert(root.rightTree,data);
			}
		}
	}

	public void in(BinarySearchTree root) {
		if (root != null) {
			in(root.leftTree);
			System.out.print(root.data + " ");
			in(root.rightTree);
		}
	}

	public void pre(BinarySearchTree root) {
		if (root != null) {
			System.out.print(root.data + " ");
			in(root.leftTree);
			in(root.rightTree);
		}
	}

	public void post(BinarySearchTree root) {
		if (root != null) {
			in(root.leftTree);
			in(root.rightTree);
			System.out.print(root.data + " ");
		}
	}

	public static void main(String[] args) {
		int[] data = new int[]{0,5,9,1,2,3,10};
		BinarySearchTree root = new BinarySearchTree(data[0]);
		for (int i = 1 ; i < data.length ; i++) {
			root.insert(root,data[i]);
		}
		root.pre(root);
	}
}
