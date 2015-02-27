import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The implement of BSTInterface
 * Name Zongwan Cao
 */

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the collection. The data in the BST
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        for (T d : data) {
            if (d == null) {
                throw new IllegalArgumentException();
            }
            add(d);
        }
    }

    @Override
    public void add(T data) {
        checkdata(data);
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            add(data, root);
        }
    }

    /**
     * Add the data as a leaf in the BST.  Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added).
     * Should have a running time of O(log n) for a balanced, and a worst case
     * of O(n).
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     * @param current the node whose data is compared with data
     */
    private void add(T data, BSTNode<T> current) {
        if (data.compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                BSTNode<T> temp = new BSTNode<T>(data);
                current.setLeft(temp);
                size++;
            } else {
                add(data, current.getLeft());
            }
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                BSTNode<T> temp = new BSTNode<T>(data);
                current.setRight(temp);
                size++;
            } else {
                add(data, current.getRight());
            }
        }
    }

    @Override
    public T remove(T data) {
        checkdata(data);
        BSTNode<T> parent = searchtree(root, null, data);
        BSTNode<T> current;
        if (parent == null) {
            current = root;
        } else {
            current = ((parent.getLeft() != null && parent.getLeft().getData()
                    == data) ? parent.getLeft() : parent.getRight());
        }
        T res = current.getData();
        if ((current.getLeft() == null) && (current.getRight() == null)) {
            if (current == root) {
                root = null;
            } else if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if ((current.getLeft() != null)
                && (current.getRight() != null)) {
            BSTNode<T> replace = current.getRight();
            BSTNode<T> replaceparent = current;
            while (replace.getLeft() != null) {
                replaceparent = replace;
                replace = replace.getLeft();
            }
            current.setData(replace.getData());
            if (current == replaceparent) {
                replaceparent.setRight(replace.getRight());
            } else {
                replaceparent.setLeft(replace.getRight());
                replace.setRight(null);
            }
        } else {
            if (current.getLeft() == null) {
                if (current == root) {
                    root = current.getRight();
                } else if (parent.getRight() == current) {
                    parent.setRight(current.getRight());
                } else {
                    parent.setLeft(current.getRight());
                }
            } else {
                if (current == root) {
                    root = current.getLeft();
                } else if (parent.getRight() == current) {
                    parent.setRight(current.getLeft());
                } else {
                    parent.setLeft(current.getLeft());
                }
            }
        }
        size--;
        return res;
    }

    /**
     * search the tree, return the parent node of the target node
     * target node means the node contains the data we passed in
     * @param data the data to be added, parent the parent of current
     * @param current the node we compared with the data
     * @param parent the parent node of current
     * @throws java.util.NoSuchElementException if there is no such data
     * @return the parent node of the target node
     */
    private BSTNode<T> searchtree(BSTNode<T> current,
            BSTNode<T> parent, T data) {
        if (current == null) {
            throw new java.util.NoSuchElementException();
        }
        if (data.compareTo(current.getData()) == 0) {
            return parent;
        } else if (data.compareTo(current.getData()) < 0) {
            return searchtree(current.getLeft(), current, data);
        } else {
            return searchtree(current.getRight(), current, data);
        }
    }

    /**
     * check the data to see if it is null
     * @param data the data to be checked
     * @throws IllegalArgumentException if the data is null
     */
    private void checkdata(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public T get(T data) {
        checkdata(data);
        BSTNode<T> current = root;
        if (root == null) {
            throw new java.util.NoSuchElementException();
        } else {
            return get(data, current);
        }
    }

    /**
     * search the tree to find data we passed in
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data Data to search for in the tree
     * @param current the node we compare with data
     * @return the data in the tree equal to the parameter
     */
    private T get(T data, BSTNode<T> current) {
        if (data.compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                throw new java.util.NoSuchElementException();
            } else {
                return get(data, current.getLeft());
            }
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                throw new java.util.NoSuchElementException();
            } else {
                return get(data, current.getRight());
            }
        } else {
            return current.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        checkdata(data);
        if (root == null) {
            return false;
        }
        return contains(data, root);
    }

    /**
     * search the tree to see if it contains the data we passed in
     * @param data Data to search for in the tree
     * @param current the node we compare with the data
     * @return whether or not the parameter is contained within the tree
     */
    private boolean contains(T data, BSTNode<T> current) {
        if (data.compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                return false;
            } else {
                return contains(data, current.getLeft());
            }
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                return false;
            } else {
                return contains(data, current.getRight());
            }
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> snapshot = new ArrayList<T>();
        preordersubtree(root, snapshot);
        return snapshot;
    }

    /**
     * preorder search subtree
     * @param snapshot the list of traversal
     * @param n the node we deal with
     */
    private void preordersubtree(BSTNode<T> n, List<T> snapshot) {
        if (n != null) {
            snapshot.add(n.getData());
            preordersubtree(n.getLeft(), snapshot);
            preordersubtree(n.getRight(), snapshot);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> snapshot = new ArrayList<T>();
        postordersubtree(root, snapshot);
        return snapshot;
    }

    /**
     * postorder search subtree
     * @param snapshot the list of traversal
     * @param n the node we deal with
     */
    private void postordersubtree(BSTNode<T> n, List<T> snapshot) {
        if (n != null) {
            postordersubtree(n.getLeft(), snapshot);
            postordersubtree(n.getRight(), snapshot);
            snapshot.add(n.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> snapshot = new ArrayList<T>();
        inordersubtree(root, snapshot);
        return snapshot;
    }

    /**
     * inorder search subtree
     * @param snapshot the list of traversal
     * @param n the node we deal with
     */
    private void inordersubtree(BSTNode<T> n, List<T> snapshot) {
        if (n != null) {
            inordersubtree(n.getLeft(), snapshot);
            snapshot.add(n.getData());
            inordersubtree(n.getRight(), snapshot);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> snapshot = new ArrayList<T>();
        if (root != null) {
            Queue<BSTNode<T>> fringe = new LinkedList<BSTNode<T>>();
            fringe.add(root);
            while (!fringe.isEmpty()) {
                BSTNode<T> n = fringe.remove();
                snapshot.add(n.getData());
                for (BSTNode<T> a : children(n)) {
                    fringe.add(a);
                }
            }
        }
        return snapshot;
    }

    /**
     * return all the children of a specific node
     * @param n the node whose children we need to find out
     * @return a list of children
     */
    private List<BSTNode<T>> children(BSTNode<T> n) {
        List<BSTNode<T>> snapshot = new ArrayList<BSTNode<T>>();
        if (n.getLeft() != null) {
            snapshot.add(n.getLeft());
        }
        if (n.getRight() != null) {
            snapshot.add(n.getRight());
        }
        return snapshot;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return height(root);
    }

    /**
     * return the height of a specific node
     * @param n the node whose height we want to figure out
     * @return the height of node n
     */
    private int height(BSTNode<T> n) {
        if (n == null) {
            return -1;
        } else {
            return Math.max(height(n.getLeft()), height(n.getRight())) + 1;
        }
    }

    @Override
    public int depth(T data) {
        checkdata(data);
        int d = 1;
        if (root == null) {
            return -1;
        }
        return depth(data, root, d);
    }

    /**
     * return the depth of a specific node
     * @param current the node whose depth we want to figure out
     * @param d the depth of current
     * @param data the data we want to find
     * @return the depth of node current
     */
    private int depth(T data, BSTNode<T> current, int d) {
        if (data.compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                return -1;
            } else {
                return depth(data, current.getLeft(), ++d);
            }
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                return -1;
            } else {
                return depth(data, current.getRight(), ++d);
            }
        } else {
            return d;
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES. DO NOT USE IT IN YOUR CODE DO
     * NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}
