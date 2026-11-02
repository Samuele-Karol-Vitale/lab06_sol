package it.unibo.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static int LOWERBOUND = 1000;
    private  static int UPPERBOUND = 2000;
    private  static int NEWELEMS = 100_000;
    private static int READINGTIMES = 1000;
    private UseListsAndMaps() {
    }

    /**
     * Program entry point.
     */
    public static void main() {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for(int i = LOWERBOUND; i < UPPERBOUND; i++) {
            numbersArray.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> numbersLinkedList = new LinkedList<>(numbersArray);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        for(int i = 0; i < numbersArray.size(); i++) {
            Integer tmp = numbersArray.get(i);
            numbersArray.set(i, numbersLinkedList.get(i));
            numbersLinkedList.set(i, tmp);
        }
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for(var i : numbersArray) {
            IO.println(i);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        List<Integer>[] lists = new List[2];
        lists[0] = numbersArray;
        lists[1] = numbersLinkedList;
        for(var list : lists) {
            long time = System.nanoTime();
            for(int i = 0; i < NEWELEMS; i++) {
                list.addFirst(i);
            }
            time = System.nanoTime() - time;
            var millis = TimeUnit.NANOSECONDS.toMillis(time);
            IO.println(
                "Adding a "
                + NEWELEMS
                + " elements to an "
                + list.getClass().getSimpleName()
                + " of integers require " + 
                + time
                + " ns or "
                + millis + " ms"
            );
        }
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        int middlePosition = numbersArray.size()/2;
        for(var list : lists) {
            long time = System.nanoTime();
            for(int i = 0; i < READINGTIMES; i++) {
                list.get(i);
            }
            time = System.nanoTime() - time;
            var millis = TimeUnit.NANOSECONDS.toMillis(time);
            IO.println(
                "Reading a "
                + READINGTIMES
                + " times in a "
                + list.getClass().getSimpleName()
                + " of integers require " + 
                + time
                + " ns or "
                + millis + " ms"
            );
        }
        
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> worldMap = Map.of(
            "Africa", 1_110_635_000L,
            "Americas", 972_005_000L,
            "Antartica", 0L,
            "Asia", 4_298_723_000L,
            "Europe", 742_452_000L,
            "Oceania", 38_304_000L
        );
        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0;
        for(var continentPopulation : worldMap.values()) {
            worldPopulation += continentPopulation;
        }
        IO.println("The world population is " + worldPopulation);
    }
}
