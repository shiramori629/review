// Java program for insertion in AVL Tree


public class AVLTree {
    AVLNode root;

    // A utility function to get the height of the tree
    int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.right) - height(N.left);
    }

    AVLNode insert(AVLNode node, int key) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new AVLNode(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance < -1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance > 1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance < -1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance > 1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print inorder traversal
    // of the tree.
    void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);

            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 24);
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 3);

        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
    }
}

class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1;
    }
}
// This code has been contributed by Mayank Jaiswal
