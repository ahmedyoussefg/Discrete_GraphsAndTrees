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

# Part 3: Inference Engine