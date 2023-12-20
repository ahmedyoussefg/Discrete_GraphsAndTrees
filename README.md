# Discrete Lab 4 ( Graphs & Trees )

---

# 1. Airline Network Shortest-Path Finder

---

# 2. Class Schedule Optimization

## Problem Statement

Imagine a school with multiple classes and subject timings where certain classes
cannot occur simultaneously due to shared resources or teacher availability. Develop
a Java program that generates an optimized class schedule by assigning time slots to
classes, ensuring that no conflicting classes occur at the same time.

- Represent the schedule information as a graph where nodes represent classes,
and edges denote conflicting timings between classes.
- Implement a graph coloring algorithm to assign distinct colors (time slots) to
nodes (classes) in the graph. Ensure that adjacent nodes (classes) linked by
edges (conflicting timings) do not share the same color (time slot) to avoid
scheduling conflicts.
- Display the timetable with color-coded class timings, ensuring that conflicting
classes have different colors (non-overlapping timings).
- Use any color names as you like.

## **Description**

The Class Schedule Optimization program employs the Welsh-Powell algorithm to generate an optimal class schedule. The algorithm follows a graph coloring approach, treating classes as vertices and conflicts as edges in a graph. The process includes calculating degrees, ordering vertices, and assigning colors.

**The Welsh-Powell algorithm** provides a heuristic, greedy solution, producing locally optimal colorings to minimize conflicts. The implementation ensures that conflicting classes receive different colors, creating a non-overlapping timetable. The algorithm, known for its efficiency in sparse graphs, contributes to effective class scheduling optimization.

## Used Data Structures

1. **Map<String, Set<String>>:**
    - **Purpose:** Represents conflicts between classes.
    - **Explanation:** A map where class names serve as keys, and the corresponding values are sets of classes with conflicting timings. This structure efficiently captures class conflicts.
2. **Map<String, String>:**
    - **Purpose:** Stores the final schedule with color-coded class timings.
    - **Explanation:** A map where class names are keys, and values are color names assigned to represent distinct time slots. This map ensures that conflicting classes have different colors, preventing scheduling conflicts.
3. **List<String>:**
    - **Purpose:** Defines a list of color names.
    - **Explanation:** A list containing color names used for assigning time slots to classes. This list helps ensure a variety of colors for optimal visual representation in the timetable.
4. **Set<String>:**
    - **Purpose:** Records classes with conflicts during color assignment.
    - **Explanation:** A set that keeps track of classes with conflicting timings during the color assignment phase. This set aids in determining available colors for each class.

## **Methods**

1. **`addClass(String className)`**
    - **Purpose:** Adds a class to the conflicts map if not already present.
    - **Explanation:** Checks if the class exists in the conflicts map and adds it with an empty set if not found. Ensures that each class has an entry in the conflicts map.
    
    ```java
    public void addClass(String className) {
        if (!conflicts.containsKey(className)) {
            conflicts.put(className, new HashSet<>());
        }
    }
    ```
    
2. **`addConflict(String class1, String class2)`**
    - **Purpose:** Adds conflicting classes to the conflicts map.
    - **Explanation:** Adds them to the conflict map if not already present then adds entries for both **`class1`** and **`class2`** to each other's set of conflicting classes in the conflicts map. Establishes the conflict relationship between classes.
    
    ```java
    public void addConflict(String class1, String class2) {
        if (!conflicts.containsKey(class1)) {
            addClass(class1);
        }
        if (!conflicts.containsKey(class2)) {
            addClass(class2);
        }
        conflicts.get(class1).add(class2);
        conflicts.get(class2).add(class1);
    }
    ```
    
3. **`colorClass(String className)`**
    - **Purpose:** Assigns a color to a class using the Welsh-Powell algorithm.
    - **Explanation:** Checks for conflicting classes and assigns the first available color (time slot) from the list of colors to the specified class. Ensures that conflicting classes have different colors.
    
    ```java
    public void colorClass(String className) {
        if (!conflicts.containsKey(className)) {
            addClass(className);
        }
        Set<String> conflictSet = conflicts.get(className);
        Set<String> usedColors = new HashSet<>();
        for (String conflict : conflictSet) {
            if (schedule.containsKey(conflict)) {
                usedColors.add(schedule.get(conflict));
            }
        }
        for (String color : colors) {
            if (!usedColors.contains(color)) {
                schedule.put(className, color);
                return;
            }
        }
        throw new RuntimeException("Maximun number of classes reached");
    }
    ```
    
4. **`optimizeSchedule()`**
    - **Purpose:** Optimizes the class schedule using graph coloring.
    - **Explanation:** Sorts classes based on the number of conflicts (degree) in descending order and applies the **`colorClass`** method to assign colors. Ensures that classes with more conflicts are processed first, increasing the likelihood of optimal coloring.
    
    ```java
    public void optimizeSchedule() {
        List<String> classes = new ArrayList<>(conflicts.keySet());
        classes.sort((class1, class2) -> conflicts.get(class2).size() - conflicts.get(class1).size());
        for (String className : classes) {
            if (!schedule.containsKey(className)) {
                colorClass(className);
            }
        }
    }
    ```
    
5. **`printSchedule()`**
    - **Purpose:** Displays the optimized class schedule with color-coded timings.
    - **Explanation:** Prints the class names along with their assigned colors in the final schedule. Provides a visual representation of the optimized timetable.
    
    ```java
    public void printSchedule() {
        System.out.println("Optimized Class Schedule:");
        for (String className : schedule.keySet()) {
            System.out.println(className + " - " + schedule.get(className));
        }
    }
    ```
    
6. **`main(String[] args)`**
    - **Purpose:** Entry point for the program, handles user input, and executes the optimization process.
    - **Explanation:** Takes user input for class names and conflicts, invokes methods to build the conflicts map, optimize the schedule, and print the final timetable. Facilitates interaction with the user for program execution.
    
    ```java
    public static void main(String[] args) {
        ClassScheduleOptimization cso = new ClassScheduleOptimization();
        Scanner in = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            System.out.print("Classes: ");
            input = in.nextLine();
        }
        String[] classes = input.split(",");
        for (String className : classes) {
            cso.addClass(className.trim());
        }
        System.out.println("Conflicting classes (cannot occur simultaneously):");
        while (true) {
            String conflict = in.nextLine();
            if (conflict.isEmpty()) {
                break;
            }
            String[] conflictClasses = conflict.split("-");
            if (conflictClasses.length != 2) {
                System.out.println("Invalid input");
                continue;
            }
            cso.addConflict(conflictClasses[0].trim(), conflictClasses[1].trim());
            cso.addConflict(conflictClasses[1].trim(), conflictClasses[0].trim());
        }
        in.close();
        cso.optimizeSchedule();
        cso.printSchedule();
    }
    ```
    

## Sample Runs & Test Cases
![test1.png](./ClassScheduleOptimization/test%20cases/test1.png)
![test2.png](./ClassScheduleOptimization/test%20cases/test2.png)


## Assumptions

- Input and output are in the same format of the lab manual.
- The chromatic number of the graph needs to be less than 55 due to the number of colors.

---

# 3. Tree Traversal

## Problem Statement

Implement three algorithms for Binary Tree traversal recursively or iteratively.
- Preorder
- Inorder
- Postorder


## **Description**

This Java program focuses on the implementation of binary tree traversal methods, including Inorder, Preorder, and Postorder. The user is prompted to enter values for the nodes of the binary tree, and the program constructs the tree accordingly. The traversals are then performed and displayed.

## Used Data Structures

1. **TreeNode:**
    - **Purpose:** Represents a node in a binary tree.

    - **Explanation:**
        - **`int data:`** Represents the value of the node.
        - **`TreeNode left_child:`** Represents the left child of the current node.
        - **`TreeNode right_child:`** Represents the right child of the current node.
    ```java
    public class TreeNode {
        private int data;
        private TreeNode left_child;
        private TreeNode right_child;

        // Constructors and getter/setter methods
    }
    ```
2. **BinaryTree**
    - **Purpose:** Represents a binary tree.
    - **Explanation:**
        - **`TreeNode root:`** Represents the root node of the binary tree.
    ```java
    public class BinaryTree {
        private TreeNode root;

        // Constructors and getter/setter methods

        // Traversal methods
        ArrayList<Integer> traverseInOrder();
        ArrayList<Integer> traversePreOrder();
        ArrayList<Integer> traversePostOrder();
    }
    ```
3. **Queue**
    - **Purpose:** Represents a queue.
    - **Explanation:** Used for breadth-first traversal of the binary tree.
4. **Stack**
    - **Purpose:** Represents a stack.
    - **Explanation:** Used in the iterative implementation of Inorder traversal.
5. **ArrayList**
    - **Purpose:** Represents a dynamic array.
    - **Explanation:** Used for storing the results of tree traversals.

## **Methods**

1. **`traverseInOrder()`**
    - **Purpose:** Performs an in-order traversal of the binary tree and returns the elements in sorted order.
    - **Explanation:** 
        - If the tree is empty (root is `null`), an empty `ArrayList` is returned.
        - Initializes an empty `ArrayList` to store the in-order traversal result.
        - Uses a stack (`nodeStack`) to keep track of nodes during traversal.
        - Starts from the root and traverses to the furthest left leaf while pushing nodes onto the stack.
        - Pops a node from the stack, adds its data to the result, and moves to its right child.
        - Continues this process until all nodes are visited, ensuring an in-order sequence.
        - Returns the `ArrayList` containing the in-order traversal result.

    This method provides an efficient iterative implementation of in-order traversal, avoiding recursion and utilizing a stack to mimic the call stack. It produces a sorted list of elements for a binary search tree.
    
    ```java
    ArrayList<Integer> traverseInOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> in_order = new ArrayList<>();
        TreeNode curr = this.root;
        Stack<TreeNode> nodeStack = new Stack<>();
        while (curr != null || !nodeStack.empty()) {
            // reach to the furthest left leaf
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.getLeftChild();
            }
            curr = nodeStack.pop();
            in_order.add(curr.getData());

            // traverse the right child of the furthest left leaf
            curr = curr.getRightChild();
        }
        return in_order;
    }
    ```
    
2. **`traversePreOrder()`**
    - **Purpose**: Performs a pre-order traversal of the binary tree and returns the elements in the order of root, left subtree, and right subtree.
    - **Explanation**:
      - If the tree is empty (root is `null`), an empty `ArrayList` is  returned.
      - Initializes an empty `ArrayList` to store the pre-order traversal   result.
      - Calls the private helper method `traversePreOrder` to perform the   recursive traversal starting from the root.
      - The helper method recursively traverses the tree in pre-order,  adding each node's data to the result list.
      - Returns the `ArrayList` containing the pre-order traversal result.

    This method provides a recursive implementation of pre-order traversal, visiting nodes in the order of root, left subtree, and right subtree. It results in a list representing the binary tree's pre-order sequence.
    
    ```java
    public ArrayList<Integer> traversePreOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> pre_order = new ArrayList<>();
        traversePreOrder(this.root, pre_order);
        return pre_order;
    }

    private void traversePreOrder(TreeNode root, ArrayList<Integer> pre_order) {
        if (root == null) {
            return;
        }
        pre_order.add(root.getData());
        traversePreOrder(root.getLeftChild(), pre_order);
        traversePreOrder(root.getRightChild(), pre_order);
    }
    ```
    
3. **`traversePostOrder()`**
   - **Purpose**: Performs a post-order traversal of the binary tree and returns the elements in the order of left subtree, right subtree, and root.
    - **Explanation**:
      - If the tree is empty (root is `null`), an empty `ArrayList` is  returned.
      - Initializes an empty `ArrayList` to store the post-order    traversal result.
      - Calls the private helper method `traversePostOrder` to perform  the recursive traversal starting from the root.
      - The helper method recursively traverses the tree in post-order,     adding each node's data to the result list.
      - Returns the `ArrayList` containing the post-order traversal     result.

    This method provides a recursive implementation of post-order traversal, visiting nodes in the order of left subtree, right subtree, and root. It results in a list representing the binary tree's post-order sequence.
    
    ```java
    public ArrayList<Integer> traversePostOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> post_order = new ArrayList<>();
        traversePostOrder(this.root, post_order);
        return post_order;
    }

    private void traversePostOrder(TreeNode root, ArrayList<Integer> post_order) {
        if (root == null) {
            return;
        }
        traversePostOrder(root.getLeftChild(), post_order);
        traversePostOrder(root.getRightChild(), post_order);
        post_order.add(root.getData());
    }
    ```
    
4. **`Main Class`**
    - **Purpose**: Contains the main method for executing the binary tree traversal program.
    - **Explanation**:
      - Initializes a `Queue<TreeNode>` to manage tree nodes for    level-wise construction.
      - Takes user input for the value of the root node and creates the     root node.
      - Adds the root node to the queue for further expansion.
      - Utilizes a while loop to iteratively prompt the user for left and   right child values of each node in the queue, allowing the    construction of the binary tree.
      - The user can input `-1` to skip adding a child.
      - Constructs a `BinaryTree` object with the root node.
      - Performs pre-order, in-order, and post-order traversals on the  binary tree.
      - Displays the results of each traversal type.
    
    ```java
    public class Main {
        public static void main(String[] args) {
            Queue<TreeNode> treeNodes = new LinkedList<>();
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the value of the root node: ");
            int root_value = in.nextInt();
            TreeNode root = new TreeNode(root_value);
            treeNodes.add(root);
            while (!treeNodes.isEmpty()) {
                TreeNode current = treeNodes.poll();
                System.out.printf("Enter left child value of %d (or -1 to   skip): \n", current.getData());
                int child = in.nextInt();
                if (child != -1) { // left child
                    TreeNode left_child = new TreeNode(child);
                    current.setLeftChild(left_child);
                    treeNodes.add(left_child);
                }
                System.out.printf("Enter right child value of %d (or -1     to skip): \n", current.getData());
                child = in.nextInt();
                if (child != -1) {
                    TreeNode right_child = new TreeNode(child);
                    current.setRightChild(right_child);
                    treeNodes.add(right_child);
                }
            }
            BinaryTree binaryTree = new BinaryTree(root);
            ArrayList<Integer> preorder_traversal = binaryTree. traversePreOrder();
            ArrayList<Integer> inorder_traversal = binaryTree.  traverseInOrder();
            ArrayList<Integer> postorder_traversal = binaryTree.    traversePostOrder();
            System.out.print("Preorder Traversal: ");
            for (int node : preorder_traversal) {
                System.out.print(node + " ");
            }
            System.out.println();
            System.out.print("Inorder Traversal: ");
            for (int node : inorder_traversal) {
                System.out.print(node + " ");
            }
            System.out.println();
            System.out.print("Postorder Traversal: ");
            for (int node : postorder_traversal) {
                System.out.print(node + " ");
            }
        }
    }
    ```

## Sample Runs & Test Cases
![test1.png](./TreeTraversal/test%20cases/test1.png)
![test2.png](./TreeTraversal/test%20cases/test2.png)


## Assumptions

- Input and output are in the same format of the lab manual.
- Only integers are allowed
