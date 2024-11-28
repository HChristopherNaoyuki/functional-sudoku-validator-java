package Solution;

import java.util.function.BiFunction;
import java.util.stream.IntStream;
import static java.lang.System.out;

/**
 * Disclaimer:
 * This code is a functional implementation for validating a Sudoku grid, utilizing Java Streams 
 * and higher-order functions. While the functional approach might appear elegant to some, it may be 
 * considered less efficient or harder to read compared to traditional iterative solutions. This 
 * code is intentionally written in a somewhat "cursed" style, meaning that it may be unnecessarily 
 * complex, non-optimal, and difficult to maintain. The purpose of this code is to demonstrate the use 
 * of functional programming techniques in a problem-solving context. The code is designed to check the 
 * validity of a Sudoku grid by verifying the following conditions:
 * 
 * 1. All rows contain unique values between 1 and 9.
 * 2. All columns contain unique values between 1 and 9.
 * 3. Each of the nine 3x3 subgrids contains unique values between 1 and 9.
 * 
 * For each of these conditions, Java Streams are used to process and validate the grid in a 
 * declarative manner.
 * 
 * This code may not be suited for real-world production systems, where clarity, efficiency, 
 * and maintainability are paramount.
 */

public class Solution
{
    public static void main(String[] args)
    {
        // Example of a valid Sudoku grid
        int [][] valid_grid = 
        {
            { 4, 1, 5, 6, 3, 8, 9, 7, 2 },
            { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
            { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
            { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
            { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
            { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
            { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
            { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
            { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };

        // Example of a Sudoku grid with column validation errors
        int [][] invalid_col_grid = 
        {
            { 1, 4, 5, 6, 3, 8, 9, 7, 2 },
            { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
            { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
            { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
            { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
            { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
            { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
            { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
            { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };

        // Example of a Sudoku grid with line validation errors
        int [][] invalid_line_grid = 
        {
            { 4, 1, 5, 4, 3, 8, 9, 7, 2 },
            { 3, 6, 2, 6, 7, 9, 1, 8, 5 },
            { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
            { 9, 2, 6, 3, 4, 1, 7, 5, 8 },
            { 1, 3, 8, 7, 5, 6, 4, 2, 9 },
            { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
            { 2, 5, 7, 1, 6, 4, 8, 9, 3 },
            { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
            { 6, 9, 1, 8, 2, 3, 5, 4, 7 },
        };

        // Example of a Sudoku grid with subgrid validation errors
        int [][] invalid_square_grid = 
        {
            { 4, 1, 5, 6, 3, 8, 9, 7, 2 },
            { 3, 6, 2, 4, 7, 9, 1, 8, 5 },
            { 7, 8, 9, 2, 1, 5, 3, 6, 4 },
            { 9, 2, 6, 3, 4, 1, 8, 5, 7 },
            { 1, 3, 7, 8, 5, 6, 4, 2, 9 },
            { 5, 7, 4, 9, 8, 2, 6, 3, 1 },
            { 2, 5, 8, 1, 6, 4, 7, 9, 3 },
            { 8, 4, 3, 5, 9, 7, 2, 1, 6 },
            { 6, 9, 1, 7, 2, 3, 5, 4, 8 },
        };

        // Testing with different grids
        out.println("Valid");
        out.println(is_valid_sodoku(valid_grid));           // Expected to be valid
        out.println(is_valid_sodoku(invalid_col_grid));     // Expected to be invalid
        out.println(is_valid_sodoku(invalid_line_grid));    // Expected to be invalid
        out.println(is_valid_sodoku(invalid_square_grid));  // Expected to be invalid
    }

    /**
     * Checks if the Sudoku grid is valid by validating rows, columns, and 3x3 subgrids.
     * It uses the helper method `is_valid_predicate` for validation with three different conditions.
     * 
     * @param grid 2D array representing the Sudoku grid
     * @return true if the Sudoku grid is valid, false otherwise
     */
    static private boolean is_valid_sodoku(int [][] grid)
    {
        // Validate rows, columns, and 3x3 subgrids using the is_valid_predicate method
        return is_valid_predicate(grid, 
                                  (row, col) -> col, 
                                  (row, col) -> row) &&  // Validate columns
               is_valid_predicate(grid, 
                                  (row, col) -> row, 
                                  (row, col) -> col) &&  // Validate rows
               is_valid_predicate(grid, 
                                  (row, col) -> (row / 3 + 3 * (col / 3)), 
                                  (row, col) -> (row % 3 + 3 * (col % 3)));  // Validate subgrids
    }

    /**
     * Validates a specific predicate on the given grid (either rows, columns, or subgrids).
     * It checks if all values in a specified direction (row/column/subgrid) are distinct 
     * and within the valid Sudoku range (1 to 9).
     * 
     * @param grid 2D array representing the Sudoku grid
     * @param predi_row Function to determine the row index based on input indices
     * @param predi_col Function to determine the column index based on input indices
     * @return true if the grid satisfies the condition for the given predicate, false otherwise
     */
    static private boolean is_valid_predicate(int [][] grid, 
                                              BiFunction<Integer, Integer, Integer> predi_row, 
                                              BiFunction<Integer, Integer, Integer> predi_col)
    {
        // Iterate through all columns and rows of the grid and check if the elements are distinct and within valid range
        return IntStream.range(0, grid.length)  // Iterate over each column
                .mapToObj(colIndex -> 
                        IntStream.range(0, grid[colIndex].length)   // Iterate over each row
                                 .mapToObj(rowIndex -> 
                                       grid[predi_row.apply(rowIndex, colIndex)][predi_col.apply(rowIndex, colIndex)]))
                .allMatch(row ->    // Ensure all values are distinct and between 1 and 9
                    row.distinct().mapToInt(i -> i).mapToObj(i -> i >= 1 && i <= 9)
                       .filter(i -> i).count() == 9);
    }
}
