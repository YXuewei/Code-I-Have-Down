public class ArrayHeapChecker {

	//Check if the given array is a representation of a binary tree
	public static boolean isBinaryTree(Integer[] array) {
		// TODO: implement this
		return true;
	}
	
	//Check if the given array is a complete binary tree
	public static boolean isCompleteBinaryTree(Integer[] array) {
		if(!isBinaryTree(array)) {
			return false;
		}
		// TODO: implement this
		return true;
	}
	
	//Check if the given array is a min-heap
	public static boolean isMinHeap(Integer[] array) {
		if(!isCompleteBinaryTree(array)) {
			return false;
		}
		// TODO: implement this
		return true;
	}
}