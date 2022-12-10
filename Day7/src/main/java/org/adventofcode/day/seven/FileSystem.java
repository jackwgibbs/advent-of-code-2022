package org.adventofcode.day.seven;

import java.util.*;

public class FileSystem {
    private Stack<String> currentPath = new Stack<>();
    private HashMap<String, Integer> directorySizes = new HashMap<>();

    private int totalDiskSpace = 70000000;

    private int spaceForUpdate = 30000000;

    public FileSystem(){
        // initialise with the root directory
        directorySizes.put("/", 0);
    }
    /**
     * Method to read input file, process commands, and return map of all directories and their sizes
     * @param scanner - input file scanner object
     * @return map of directories and their corresponding sizes
     */
    public HashMap<String, Integer> getDirectorySizes(Scanner scanner){
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            // split line on spaces
            String[] lineSplit = line.split(" ");

            // check if line is a cd command or a file size
            if ((lineSplit[0].equals("$")) && lineSplit[1].equals("cd")){
                if (lineSplit[2].equals("..")){
                    // if command is 'cd ..' pop off current directory from arraylist
                    currentPath.pop();
                }else{
                    // if command cd 'cd directory', add directory to arrayList
                    currentPath.add(lineSplit[2]);
                }
            }else if(lineSplit[0].equals("dir")){
                // if line is a directory, initialise it in the directory sizes array list
                directorySizes.put(getPath(currentPath)+lineSplit[1]+"/", 0);

            }else if(!lineSplit[0].equals("$")){
                // if line is listing a file and its size, add to corresponding directories
                int fileSize = Integer.parseInt(lineSplit[0]);
                addFileSize(fileSize);
            }
        }
        return directorySizes;
    }

    /**
     * Method to update file sizes given a current directory and a file
     * @param fileSize - size of the current file being analysed
     */
    public void addFileSize(int fileSize){
        // loop over current directory path and add file size to directories in path
        Stack<String> filePath = (Stack<String>) currentPath.clone();

        while(!filePath.empty()) {
            // add file size to all directories in the path
            String p = getPath(filePath);
            int currentDirectorySize = directorySizes.get(p);
            int newDirectorySize = currentDirectorySize + fileSize;
            directorySizes.put(p, newDirectorySize);
            filePath.pop();
        }
    }

    /**
     * Method to return sum of directories size below specified size
     * @param size - size to filter directories by
     * @return sum of directories sizes below specified size
     */
    public int sumOfDirectorySizesOverSize(int size) {
        int total = 0;
        for (String directory : directorySizes.keySet()) {
            if (directorySizes.get(directory) <= size) {
                total += directorySizes.get(directory);
            }
        }
        return total;
    }

    /**
     * Method to convert the current path to a String
     * @param pathStack - path represented as a Stack
     * @return path as a String
     */
    public String getPath(Stack<String> pathStack){
        StringBuilder path = new StringBuilder();
        for (String s : pathStack) {
            // if directory is '/', do not add another /
            path.append(s.equals("/") ? "/" : s + "/");

        }
        return path.toString();
    }

    /**
     * Method to find the smallest directory to delete that provides system enough space for an update
     * @return the size of the smallest directory to delete
     */
    public int findSmallestDirectoryToDeleteForUpdate(){
        int smallestDirectorySize = 70000000; // initialise to a large value
        int diskSpaceUsed = directorySizes.get("/");
        int diskSpaceRemaining = totalDiskSpace - diskSpaceUsed;

        for (String directory : directorySizes.keySet()) {
            // check whether deleting this directory provides enough space for update
            if( (diskSpaceRemaining + directorySizes.get(directory)) > spaceForUpdate ){
                if (directorySizes.get(directory) < smallestDirectorySize){
                    smallestDirectorySize = directorySizes.get(directory);
                }
            }
        }
        return smallestDirectorySize;
    }
}
