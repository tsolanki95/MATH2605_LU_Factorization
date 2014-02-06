/**
 * Provides basic matrix operations for use in Linear Algebra.
 * 
 * @author Tanmay Solanki
 * @version 0.1
 */
public class MatrixOperator {
	
	/**
	 * Finds the determinant of a matrix recursively through the use of a cofactor expansion algorithm.
	 * 
	 * @param matrix
	 * @return the determinant of the matrix
	 * @throws IllegalMatrixException
	 */
	public static double cofactor(double[][] matrix) throws IllegalMatrixException {
		
		double sum = 0;
		
		if(NotSquareMatrix(matrix) || matrix.length < 1 || matrix[0].length < 1) { //check for illegal matrices
			throw(new IllegalMatrixException("Error, Cannot find determinant of this array by Cofactor expansion"));
		}
		
		if(matrix.length == 2) { //base case
			return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
		}
		
		if(matrix.length == 1) { //also possible base case
			return matrix[0][0];
		}
		
		//changes the sign based on the column of the matrix
		double[] signs = new double[matrix.length];
		
		for (int i = 0; i < signs.length; i++) {
			if (i % 2 == 0) {
				signs[i] = 1;
			} else {
				signs[i] = -1;
			}
		}
		
		//expands the matrix recursively doubleo smaller matrix.
		for (int m = 0; m < matrix.length; m++) {
			sum = sum + (signs[m] * matrix[0][m] * cofactor(removeRowCol(matrix, 0, m)));
		}
		
		return sum;
	}
	
	/**
	 * determines whether the matrix is square or not
	 * 
	 * @param matrix
	 * @return true if the matrix is not square, false otherwise
	 */
	private static boolean NotSquareMatrix(double[][] matrix) {
		return (matrix.length != matrix[0].length);
	}
	
	/**
	 * creates new Array and removes a specific row and column from the original Array in this new one
	 * 
	 * @param matrix
	 * @param removeRow the row to be removed in the new matrix
	 * @param removeCol the column to be removed in the new matrix
	 * @return the newMatrix.
	 */
	private static double[][] removeRowCol(double[][] matrix, double removeRow, double removeCol) {
		double[][] newMatrix = new double[matrix.length-1][matrix.length-1];
		int i = 0;
		int j = 0;
		
		for (int m = 0; m < matrix.length; m++) {
			for (int n = 0; n < matrix[0].length; n++) {
				if (m != removeRow || n != removeCol) {
					newMatrix[i][j++] = matrix[m][n];
				}
			}
			i++;
		}
		
		return newMatrix;
	}
	
	/**
	 * Multiplies two matrices A and B together
	 * 
	 * @param double array A
	 * @param double array B
	 * @return new array formed by multiplying A(B) in that order
	 * @throws IllegalMatrixException
	 */
	public static double[][] multiply(double[][] A, double[][] B) throws IllegalMatrixException {
		int aRow = A.length;
		int aCol = A[0].length;
		int bRow = B.length;
		int bCol=  B[0].length;
		double[][] newMatrix = new double[aRow][bCol];
		
		if (aCol != bRow) {
			throw new IllegalMatrixException("Error, Matrices could not be multiplied due to mismatch in number of rows and columns.");
		}
		
		for (int i = 0; i < aRow; i++) {
			for (int j = 0; j < bCol; j++) {
				for (int k = 0; k < aCol; k++) {
					newMatrix[i][j] += A[i][k] *B[k][j];
				}
			}
		}
		
		return newMatrix;
	}

}
