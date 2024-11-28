# Sudoku Validation in Java

This repository contains a functional Java solution for validating a Sudoku grid using Java Streams and higher-order functions. The code checks whether a given 9x9 Sudoku grid adheres to the rules of Sudoku, ensuring the following:

1. **All rows contain unique values between 1 and 9.**
2. **All columns contain unique values between 1 and 9.**
3. **Each of the nine 3x3 subgrids (also called boxes) contains unique values between 1 and 9.**

The solution leverages Java Streams to process and validate the grid in a declarative and functional programming style. While this approach is elegant, it may be less efficient or harder to read than traditional iterative solutions.

## Features

- **Functional Programming:** Uses Java's Stream API and higher-order functions (`BiFunction` and `IntStream`) to validate the grid in a declarative way.
- **Grid Validation:** Checks rows, columns, and 3x3 subgrids for the validity of the Sudoku grid.
- **Test Cases:** Includes examples of both valid and invalid Sudoku grids to demonstrate the validation process.
  
## How It Works

The code defines a method `is_valid_sodoku(int[][] grid)` that checks the validity of the Sudoku grid by:
1. Validating rows for uniqueness.
2. Validating columns for uniqueness.
3. Validating each of the nine 3x3 subgrids for uniqueness.

It uses the `is_valid_predicate` method to handle these validations with functional style. For each condition (rows, columns, and subgrids), the method is called with different parameters to map through the grid and verify the validity of values.

## Example Grids

### Valid Sudoku Grid

```java
int[][] valid_grid = {
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
```

### Invalid Sudoku Grid (Column Validation Error)

```java
int[][] invalid_col_grid = {
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
```

### Invalid Sudoku Grid (Row Validation Error)

```java
int[][] invalid_line_grid = {
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
```

### Invalid Sudoku Grid (Subgrid Validation Error)

```java
int[][] invalid_square_grid = {
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
```

## Usage

To run the Sudoku validation, simply call the `is_valid_sodoku(int[][] grid)` method with a 9x9 integer array representing your Sudoku grid. The method will return `true` if the grid is valid according to Sudoku rules, and `false` otherwise.

Example:

```java
int[][] grid = {
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

boolean isValid = is_valid_sodoku(grid);
System.out.println(isValid ? "Valid Sudoku" : "Invalid Sudoku");
```

---
