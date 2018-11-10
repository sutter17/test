import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Sutter Phillips
 * @author Khalil Adams
 *
 */
public class HuffmanTree {
	
	
	/**
	 * @author Sutter Phillips
	 * @author Khalil Adams
	 * HuffNode class contains nodes to build a tree
	 * Compares Nodes based on frequency, allowing priority queue to prioritize the most used elements
	 */
	private class HuffNode implements Comparable<HuffNode>{
		int value;
		int frequency;
		HuffNode left;
		HuffNode right;

		/**
		 * @param value - ASCII code
		 * @param frequency - Frequency of letter
		 */ 
		public HuffNode(int value, int frequency) {
			this.value = value;
			this.frequency = frequency;

		}
		/**
		 * @param left 
		 * @param right
		 * combines left and right nodes into a tree under a single node with value -1
		 */
		public HuffNode(HuffNode left, HuffNode right) {
			frequency = left.frequency + right.frequency;
			this.left = left;
			this.right = right;
			value = -1;
		}
		/**
		 * @param value - ASCII code
		 * constructor for unknown frequencies
		 */
		public HuffNode(int value) {
			this.value = value;
		}
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(HuffNode other) {
			return this.frequency-other.frequency;
		}
	}

	HuffNode root;
	private static final int CHAR_MAX = 256;
	/**
	 * @param count - array with position corresponding to ASCII codes, and value corresponding to frequency
	 * constructs a forest, then collects them into one tree
	 */
	public HuffmanTree(int[] count) {
		PriorityQueue<HuffNode> forest = new PriorityQueue<HuffNode>();
		for(int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				HuffNode myNode = new HuffNode(i,count[i]);
				forest.add(myNode); 
			}
		}
		forest.add(new HuffNode(CHAR_MAX,1));
		root = collect(forest);

	}

	/**
	 * @param forest - priotity queue filled with roots of trees
	 * @return - single root node of a tree containing all original nodes
	 */
	private HuffNode collect(PriorityQueue<HuffNode> forest){
		while(forest.size()>1) {
			forest.add(new HuffNode(forest.remove() , forest.remove()));
		}
		return forest.remove();
	}
	public void write (PrintStream output) {
		write(output,root,"");
	}
	/**
	 * @param output - place to write values
	 * @param root - root which we are directed to
	 * @param code - position in tree
	 */
	public void write (PrintStream output, HuffNode root, String code) {
		if(root == null) {
			return ;
		}
		if(root.right == null && root.left == null) {
			output.println(root.value);
			output.println(code);
			return ;
		}
		code = code + "0";
		write(output, root.left,code);
		code = code.substring(0,code.length()-1) + "1";
		write(output, root.right,code);
	}
	/**
	 * @param node - node to be added
	 * @param current - node to follow code down
	 * @param code - path to follow
	 */
	private void add(HuffNode node, HuffNode current,String code) {
		if(code.length() == 1) {
			if(code.equals("1")) {
				current.right = node;
			}else {
				current.left = node;
			}
		}else {
			if(code.charAt(0) =='1') {
				if(current.right == null) {
					current.right = new HuffNode(-1);
				}
				add(node,current.right,code.substring(1));
			}else {	
				if(current.left ==null) {
					current.left = new HuffNode(-1);
				}
				add(node,current.left,code.substring(1));
			}
		}
	}
	
	/**
	 * @param output - list of ASCII values and codes
	 */
	public HuffmanTree(Scanner output) {
		root = new HuffNode(-1);
		while (output.hasNext()) {
			int n = Integer.parseInt(output.next());
			String code = output.next();
			add(new HuffNode(n),root,code);
		}
	}
	/**
	 * @param input - input file, huffed binary stream
	 * @param output - place to write ASCII values
	 * @param eof - end of file
	 */
	void decode(BitInputStream input, PrintStream output, int eof) {
		HuffNode current = root;
		int code = input.readBit();
		while(code != -1) {
			if(current.value == -1) {
				if (code == 1) {
					current = current.right;
				}else {
					current= current.left;
				}
				code = input.readBit();
			}else {
				if(current.value==CHAR_MAX) {
					break;
				}
				output.write(current.value);
				current = root;
			}
		}


	}
}
