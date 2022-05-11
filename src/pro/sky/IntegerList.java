package pro.sky;

public interface IntegerList {
    int add(int item);
    int add(int index, int item);
    int set(int index, int item);
    int removeByItemValue(int item);
    int removeByItemIndex(int index);
    boolean contains(int item);
    int indexOf(int item);
    int lastIndexOf(int item);
    int get(int index);
    boolean equals(IntegerList otherList);
    int size();
    boolean isEmpty();
    void clear();
    int[] toArray();
    void generateRandomArrayList(int items);
    void measureTimeOfBubbleSortMethod();
    void measureTimeOfSelectionSortMethod();
    void measureTimeOfInsertionSortMethod();
}
