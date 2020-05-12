package com.example.wordsearch;

import java.util.Arrays;
import java.util.Random;

public class WordSearchGenerator {
    int max;
    String [] words;
    String[][] wordSearchTable;

    public WordSearchGenerator(String[] words, int x) {
        setMax(x-1);
        setWords(words);
        setWordSearchTable(generateWordSearchTable());
    }

    public int getMax() {return max;}

    public void setMax(int max) {this.max = max;}

    public String[] getWords() {return words;}

    public void setWordSearchTable(String[][] wordSearchTable) {this.wordSearchTable = wordSearchTable;}

    public void setWords(String[] words) {this.words = words;}

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String [] words = new String[]{"SWIFT", "KOTLIN", "OBJECTIVEC", "VARIABLE", "JAVA", "MOBILE","SHOPIFY"};
//        WordSearchGenerator wordSearch = new WordSearchGenerator(words, 15);
//        System.out.println(Arrays.deepToString(wordSearch.wordSearchTable).replace("], ", "]\n"));
//    }

    public String[][] generateWordSearchTable(){
        String[][] grid = new String[getMax()+1][getMax()+1];
        int count = 0;

        while(count < getWords().length) {
            Random rand = new Random();
            int randomRow = rand.nextInt((getMax() - 0) + 1) + 0;
            int randomCol = rand.nextInt((getMax() - 0) + 1) + 0;

            if(checkUp(randomRow,randomCol, grid,getWords()[count])) {
                putUp(randomRow,randomCol, grid,getWords()[count]);
                count++;

            }else if(checkTopRightDiag(randomRow,randomCol, grid,getWords()[count])) {
                putTopRightDiag(randomRow,randomCol, grid, getWords()[count]);
                count++;

            }else if(checkRight(randomRow,randomCol, grid, getWords()[count])) {
                putRight(randomRow,randomCol, grid, getWords()[count]);
                count++;

            }else if(checkBottomRightDiag(randomRow,randomCol, grid,getWords()[count])) {
                putBottomRightDiag(randomRow,randomCol, grid,getWords()[count]);
                count++;

            }else if(checkDown(randomRow,randomCol, grid,getWords()[count])) {
                putDown(randomRow,randomCol, grid,getWords()[count]);
                count++;

            }else if(checkBottomLeftDiag(randomRow,randomCol, grid,getWords()[count])) {
                putBottomLeftDiag(randomRow,randomCol, grid, getWords()[count]);
                count++;

            }else if(checkLeft(randomRow,randomCol, grid, getWords()[count])) {
                putLeft(randomRow,randomCol, grid, getWords()[count]);
                count++;

            }else if(checkTopLeftDiag(randomRow,randomCol, grid, getWords()[count])) {
                putTopLeftDiag(randomRow,randomCol, grid, getWords()[count]);
                count++;
            }

        }

        fillGrid(grid);

        return grid;
    }



    public static boolean checkUp(int row, int col, String[][] arr, String word) {
        boolean up = false;

        for(int i=0; i<word.length(); i++) {
            if(row - i < 0) {
                up = false;
                break;
            }else if(arr[row-i][col] == null || arr[row-i][col].equals(Character.toString(word.charAt(i)))) {
                up = true;
            }else {
                up = false;
                break;
            }
        }

        return up;
    }

    public boolean checkDown(int row, int col, String[][] arr, String word) {
        boolean down = false;

        for(int i=0; i<word.length(); i++) {
            if(row + i > getMax()) {
                down = false;
                break;
            }else if(arr[row+i][col] == null || arr[row+i][col].equals(Character.toString(word.charAt(i)))) {
                down = true;
            }else {
                down = false;
                break;
            }
        }

        return down;
    }

    public boolean checkRight(int row, int col, String[][] arr, String word) {
        boolean right = false;

        for(int i=0; i<word.length(); i++) {
            if(col + i > getMax()) {
                right = false;
                break;
            }else if(arr[row][col+i] == null || arr[row][col+i].equals(Character.toString(word.charAt(i)))) {
                right = true;
            }else {
                right = false;
                break;
            }
        }

        return right;
    }

    public static boolean checkLeft(int row, int col, String[][] arr, String word) {
        boolean left = false;

        for(int i=0; i<word.length(); i++) {
            if(col - i < 0) {
                left = false;
                break;
            }else if(arr[row][col-i] == null || arr[row][col-i].equals(Character.toString(word.charAt(i)))) {
                left = true;
            }else {
                left = false;
                break;
            }
        }

        return left;
    }

    public boolean checkBottomRightDiag(int row, int col, String[][] arr, String word) {
        boolean diag = false;

        for(int i=0; i < word.length(); i++) {
            if(row + i > getMax()|| col + i > getMax()) {
                diag = false;
                break;
            }else if(arr[row+i][col+i] == null || arr[row+i][col+i].equals(Character.toString(word.charAt(i)))) {
                diag = true;
            }else {
                diag = false;
                break;
            }
        }

        return diag;

    }

    public boolean checkBottomLeftDiag(int row, int col, String[][] arr, String word) {
        boolean diag = false;

        for(int i=0; i < word.length(); i++) {
            if(row + i > getMax() || col - i < 0) {
                diag = false;
                break;
            }else if(arr[row+i][col-i] == null || arr[row+i][col-i].equals(Character.toString(word.charAt(i)))) {
                diag = true;
            }else {
                diag = false;
                break;
            }
        }

        return diag;

    }

    public boolean checkTopRightDiag(int row, int col, String[][] arr, String word) {
        boolean diag = false;

        for(int i=0; i < word.length(); i++) {
            if(row - i < 0 || col + i > getMax()) {
                diag = false;
                break;
            }else if(arr[row-i][col+i] == null || arr[row-i][col+i].equals(Character.toString(word.charAt(i)))) {
                diag = true;
            }else {
                diag = false;
                break;
            }
        }

        return diag;

    }

    public static boolean checkTopLeftDiag(int row, int col, String[][] arr, String word) {
        boolean diag = false;

        for(int i=0; i < word.length(); i++) {
            if(row - i < 0 || col - i < 0) {
                diag = false;
                break;
            }else if(arr[row-i][col-i] == null || arr[row-i][col-i].equals(Character.toString(word.charAt(i)))) {
                diag = true;
            }else {
                diag = false;
                break;
            }
        }

        return diag;

    }


    public static void putUp(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row-i][col] = Character.toString(word.charAt(i));
        }
    }




    public static void putDown(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row+i][col] = Character.toString(word.charAt(i));
        }
    }

    public static void putLeft(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row][col-i] = Character.toString(word.charAt(i));
        }
    }


    public static void putRight(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row][col+i] = Character.toString(word.charAt(i));
        }
    }

    public static void putBottomRightDiag(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row+i][col+i] = Character.toString(word.charAt(i));
        }
    }

    public static void putBottomLeftDiag(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row+i][col-i] = Character.toString(word.charAt(i));
        }
    }

    public static void putTopRightDiag(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row-i][col+i] = Character.toString(word.charAt(i));
        }
    }

    public static void putTopLeftDiag(int row, int col, String[][]arr, String word) {
        for(int i=0; i<word.length(); i++) {
            arr[row-i][col-i] = Character.toString(word.charAt(i));
        }
    }

    public static void fillGrid(String [][] arr) {
        String alphabet[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (int i = 0; i<arr.length; i++){
            for (int j = 0; j<arr[i].length; j++){
                Random rand = new Random();
                int x = rand.nextInt((25 - 0) + 1) + 0;
                if(arr[i][j] == null) {
                    arr[i][j] = alphabet[x];
                }
            }
        }

    }


}
