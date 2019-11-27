package com.example.demo;

import java.util.Arrays;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: MergeSort
 * @create 2019/9/12
 * @since 1.0.0
 * <description>：
 * ==================================================
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] data = new int[]{2,34,5,2,1,7,554,22,32,213};
		MergeSort test = new MergeSort();
		test.separate(data,0,data.length-1);
		System.out.println(Arrays.toString(data));
	}

	public void separate(int[] data, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			separate(data, left, mid);
			separate(data, mid + 1, right);
			merge(data, left, mid, right);
		}
	}

	public void merge(int[] data, int left, int mid, int right) {

		int[] temp = new int[data.length];

		int curr = left;

		int indexLeft = left;

		int indexRight = mid + 1;

		while (mid >= indexLeft && right >= indexRight) {
			if (data[indexLeft] <= data[indexRight]) {
				temp[curr] = data[indexLeft];
				indexLeft++;
			} else {
				temp[curr] = data[indexRight];
				indexRight++;
			}
			curr++;
		}

		while (mid >= indexLeft) {
			temp[curr++] = data[indexLeft++];
		}

		while (right >= indexRight) {
			temp[curr++] = data[indexRight++];
		}

		for (int i = left ; i <= right ; i++) {
			data[i] = temp[i];
		}
	}
}