package org.adventofcode.day.eight;

import java.util.Scanner;

public class TreeScan {

    private final String[][] treeCover;

    /**
     * Constructor to construct 2D array of trees
     * @param fileInputScanner - the input file scanner object
     * @param columns - the number of tree columns
     */
    public TreeScan(Scanner fileInputScanner, int columns){
       treeCover = new String[columns][];
       int count = 0;

        while(fileInputScanner.hasNextLine()){
            String line = fileInputScanner.nextLine();
            String[] lineList = line.split("");
            treeCover[count++] = lineList;
        }
    }

    /**
     * Method to get the number of trees that are visible from the outside of the grid
     * @return number of trees visible
     */
    public int getVisibleTrees(){
        int visible = 0; // number of trees visible
        int treeHeight;

        for(int row = 0; row < treeCover[0].length; row++){
            for(int col = 0; col < treeCover.length; col++){
                // get height of the current tree to analyse
                treeHeight = Integer.parseInt(treeCover[row][col]);

                // if tree is an edge tree, it is visible
                if ((isEdgeTree(row, col))){
                    visible++;
                    continue;
                }

                // check if tree visible from the left
                if (visibleFromLeft(treeHeight, row, col)){
                    visible++;
                    continue;
                }

                // check if tree visible from the right
                if (visibleFromRight(treeHeight, row, col)){
                    visible++;
                    continue;
                }

                // check if tree visible from the top
                if (visibleFromTop(treeHeight, row, col)){
                    visible++;
                    continue;
                }

                // check if tree visible from the bottom
                if (visibleFromBottom(treeHeight, row, col)){
                    visible++;
                }
            }
        }

        return visible;
    }

    /**
     * Method to get the tree with the best viewing score
     * @return - the best viewing score
     */
    public int getViewingScore(){
        int bestViewingScore = 0;
        int left, right, top, bottom;
        int treeHeight;
        int treeViewingScore;
        for(int row = 1; row < treeCover[0].length - 1; row++){
            for(int col = 1; col < treeCover.length - 1; col++) {
                left = 0; right = 0; top = 0; bottom = 0; // reset variables for new tree
                treeHeight = Integer.parseInt(treeCover[row][col]);

                // get the number of trees current tree can see in each direction
                left += visibleNumberFromLeft(treeHeight, row, col);
                right += visibleNumberFromRight(treeHeight, row, col);
                top += visibleNumberFromTop(treeHeight, row, col);
                bottom += visibleNumberFromBottom(treeHeight, row, col);

                treeViewingScore = left * right * top * bottom;

                // if current trees score is the best so far, update best variable
                if (treeViewingScore > bestViewingScore){
                    bestViewingScore = treeViewingScore;
                }
            }
        }

        return bestViewingScore;
    }

    /**
     * Method to check whether the current tree is visible from outside the grid to the left
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return whether tree visible from outside the grid to the left
     */
    private Boolean visibleFromLeft(int treeHeight, int row, int column){
        // loop over each tree to the left of the current tree and check whether its height is greater
        for (int i = column-1; i >= 0; i--){
            if (Integer.parseInt(treeCover[row][i]) >= treeHeight){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get the number of trees visible from the current tree to the left
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return the number of trees visible from the current tree to the left
     */
    private int visibleNumberFromLeft(int treeHeight, int row, int column){
        int count = 0;

        // loop over each tree from the left of the current tree and check whether its height is less
        for (int i = column-1; i >= 0; i--){
            if (Integer.parseInt(treeCover[row][i]) < treeHeight){
                count++;
            }else{
                return ++count;
            }
        }
        return count;
    }

    /**
     * Method to check whether the current tree is visible from outside the grid to the right
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return whether tree visible from outside the grid to the right
     */
    private Boolean visibleFromRight(int treeHeight, int row, int column){
        // loop over each tree to the right of the current tree and check whether its height is greater
        for (int i = column + 1; i <= treeCover[0].length - 1 ; i++){
            if (Integer.parseInt(treeCover[row][i]) >= treeHeight){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get the number of trees visible from the current tree to the right
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return the number of trees visible from the current tree to the right
     */
    private int visibleNumberFromRight(int treeHeight, int row, int column){
        int count = 0;

        // loop over each tree to the right of the current tree and check whether its height is less
        for (int i = column + 1; i <= treeCover[0].length - 1 ; i++){
            if (Integer.parseInt(treeCover[row][i]) < treeHeight){
                count++;
            }else{
                return ++count;
            }
        }
        return count;
    }

    /**
     * Method to check whether the current tree is visible from outside the grid from the top
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return whether tree visible from outside the grid from the top
     */
    private Boolean visibleFromTop(int treeHeight, int row, int column){
        // loop over each tree above the current tree and check whether its height is greater
        for (int i = row - 1; i >= 0; i--){
            if (Integer.parseInt(treeCover[i][column]) >= treeHeight){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get the number of trees visible from the current tree from above
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return the number of trees visible from the current tree from above
     */
    private int visibleNumberFromTop(int treeHeight, int row, int column){
        int count = 0;

        // loop over each tree above current tree and check whether its height is less
        for (int i = row - 1; i >= 0; i--){
            if (Integer.parseInt(treeCover[i][column]) < treeHeight){
                count++;
            }else{
                return ++count;
            }
        }
        return count;
    }

    /**
     * Method to check whether the current tree is visible from outside the grid from the bottom
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return whether tree visible from outside the grid from the bottom
     */
    private Boolean visibleFromBottom(int treeHeight, int row, int column){
        // loop over each tree below the current tree and check whether its height is greater
        for (int i = row + 1; i <= treeCover[0].length - 1; i++){
            if (Integer.parseInt(treeCover[i][column]) >= treeHeight){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get the number of trees visible from the current tree from below
     * @param treeHeight - height of the current tree
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return the number of trees visible from the current tree from below
     */
    private int visibleNumberFromBottom(int treeHeight, int row, int column){
        int count = 0;

        // loop over each tree below current tree and check whether its height is less
        for (int i = row + 1; i <= treeCover[0].length - 1; i++){
            if (Integer.parseInt(treeCover[i][column]) < treeHeight){
                count++;
            }
            else{
                return ++count;
            }
        }
        return count;
    }

    /**
     * Method to check whether the current tree is an edge tree, i.e. on the outside of the grid
     * @param row - row of the current tree
     * @param column - column of the current tree
     * @return whether tree is an edge tree
     */
    private Boolean isEdgeTree(int row, int column){
        // check whether tree is on the top or left edge
        if (row == 0 || column == 0){
            return true;
        }

        // check whether tree if on the bottom or right edge
        if (row == treeCover[0].length-1 || column == treeCover.length-1){
            return true;
        }

        return false;
    }
}
