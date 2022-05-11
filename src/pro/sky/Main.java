package pro.sky;

public class Main {

    public static void main(String[] args) {

        IntegerList arrayListInteger = new ArrayListInteger();
        arrayListInteger.generateRandomArrayList(100000);
        arrayListInteger.measureTimeOfBubbleSortMethod();
        arrayListInteger.measureTimeOfSelectionSortMethod();
        arrayListInteger.measureTimeOfInsertionSortMethod();
        arrayListInteger.measureTimeOfMergeSortMethod();
        System.out.println(arrayListInteger.contains(23));
    }
}
