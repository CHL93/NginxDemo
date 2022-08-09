package com.shu.Demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // 正常的dfs 遍历所有可行路径
    // 统计 可行路径的最大值和数目
    static List<List<Integer>> pathList = new ArrayList<>();
    static int[][] dirctions = {{0,-1}, {-1,0}, {-1, -1}};

    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        board.add("E23");
        board.add("2X2");
        board.add("21S");
        pathsWithMaxScore(board);
    }
    public static int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        for (int[] d : dirctions) {
            dfs(board, n + d[0] - 1, n + d[1] - 1, new ArrayList<>());
        }
        int max = 0;
        int count = 0;
        for (List<Integer> path : pathList) {
            int val = 0;
            for (Integer num : path) {
                val += num;
            }
            if (val == max) {
                count++;
            }
            if (val > max) {
                max = val;
                count = 0;
            }
        }
        return new int[]{max, count};

    }
    public static void dfs(List<String> board, int curRow, int curCol, List<Integer> path) {
        if (curCol < 0 || curRow < 0) {
            return;
        }
        if (board.get(curRow).charAt(curCol) == 'X') {
            return;
        }
        if (curRow == 0 && curCol == 0) {
            List<Integer> list = new ArrayList<>(path);
            pathList.add(list);
            return;
        }
        path.add(board.get(curRow).charAt(curCol) - '0');
        for (int[] d : dirctions) {
            int newRow = curRow + d[0];
            int newCol = curCol + d[1];
            dfs(board, newRow, newCol, path);
        }
        path.remove(path.size() - 1);
    }

}