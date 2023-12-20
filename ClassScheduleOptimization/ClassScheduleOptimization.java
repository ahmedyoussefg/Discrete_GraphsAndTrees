package ClassScheduleOptimization;

import java.util.*;

public class ClassScheduleOptimization {
    private Map<String, Set<String>> conflicts;
    private Map<String, String> schedule;
    private List<String> colors = Arrays.asList("red", "green", "blue", "yellow", "orange", "purple", "pink", "brown",
            "black", "white", "gray", "cyan", "magenta", "lime", "olive", "maroon", "navy", "aquamarine", "turquoise",
            "silver", "limegreen", "indigo", "violet", "pink", "gold", "coral", "tan", "khaki", "lavender", "plum",
            "crimson", "teal", "azure", "beige", "mint", "lavenderblush", "linen", "ivory", "wheat", "aliceblue",
            "ghostwhite", "honeydew", "seashell", "cornsilk", "lemonchiffon", "floralwhite", "oldlace", "mintcream",
            "papayawhip", "blanchedalmond", "bisque", "moccasin", "navajowhite", "peachpuff", "mistyrose", "lavender");

    public ClassScheduleOptimization() {
        conflicts = new HashMap<>();
        schedule = new HashMap<>();
    }

    public void addClass(String className) {
        if (!conflicts.containsKey(className)) {
            conflicts.put(className, new HashSet<>());
        }
    }

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

    public void optimizeSchedule() {
        List<String> classes = new ArrayList<>(conflicts.keySet());
        classes.sort((class1, class2) -> conflicts.get(class2).size() - conflicts.get(class1).size());
        for (String className : classes) {
            if (!schedule.containsKey(className)) {
                colorClass(className);
            }
        }
    }

    public void printSchedule() {
        System.out.println("Optimized Class Schedule:");
        for (String className : schedule.keySet()) {
            System.out.println(className + " - " + schedule.get(className));
        }
    }

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
}
