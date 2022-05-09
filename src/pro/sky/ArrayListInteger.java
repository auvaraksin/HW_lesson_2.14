package pro.sky;

import java.util.Arrays;

public class ArrayListInteger implements IntegerList {
    private int[] arrayList;
    private int size = 1;

    @Override
    public int add(int item) {
        if (isEmpty()) {
            arrayList = new int[size];
            arrayList[size - 1] = item;
        } else {
            if (size == arrayList.length) {
                grow();
                size++;
                arrayList[size - 1] = item;
            } else {
                size++;
                arrayList[size - 1] = item;
            }
        }
        return item;
    }

    @Override
    public int add(int index, int item) {
        checkArrayIndexOutOfBoundsException(index);
        checkNullPointerException();
        size++;
        int[] newArrayList = new int[size];
        for (int i = 0; i < index; i++) {
            newArrayList[i] = arrayList[i];
        }
        newArrayList[index] = item;
        for (int i = index + 1; i < newArrayList.length; i++) {
            newArrayList[i] = arrayList[i - 1];
        }
        arrayList = newArrayList;
        return item;
    }

    @Override
    public int set(int index, int item) {
        checkArrayIndexOutOfBoundsException(index);
        arrayList[index] = item;
        return item;
    }

    @Override
    public int removeByItemValue(int item) {
        int count = 0;
        for (int i : arrayList) {
            if (i == item) {
                count++;
            }
        }
        if (count == 0) {
            throw new RuntimeException("Список не содержит искомый элемент");
        }
        size = size - count;
        if (size == 0) {
            arrayList = null;
            size = 1;
            return item;
        }
        count = 0;
        int[] newArrayList = new int[size];
        for (int i : arrayList) {
            if (i != item) {
                newArrayList[count] = i;
                count++;
            }
        }
        arrayList = newArrayList;
        return item;
    }

    @Override
    public int removeByItemIndex(int index) {
        checkArrayIndexOutOfBoundsException(index);
        int item = arrayList[index];
        size--;
        if (size == 0) {
            arrayList = null;
            size = 1;
            return item;
        }
        int[] newArrayList = new int[size];
        if (index == 0) {
            for (int i = 1; i < arrayList.length; i++) {
                newArrayList[i - 1] = arrayList[i];
            }
            arrayList = newArrayList;
            return item;
        }
        if (index == size) {
            for (int i = 0; i < newArrayList.length; i++) {
                newArrayList[i] = arrayList[i];
            }
            arrayList = newArrayList;
            return item;
        }
        for (int i = 0; i < index; i++) {
            newArrayList[i] = arrayList[i];
        }
        for (int i = index + 1; i < arrayList.length; i++) {
            newArrayList[i - 1] = arrayList[i];
        }
        arrayList = newArrayList;
        return item;
    }

    @Override
    public boolean contains(int item) {
        mergeSort(arrayList);
        return searchByBinaryMethod(arrayList, item);
    }

    //бинарный поиск
    private static boolean searchByBinaryMethod(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(int item) {
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = arrayList.length - 1; i >= 0; i--) {
            if (arrayList[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        checkArrayIndexOutOfBoundsException(index);
        return arrayList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        checkIllegalArgumentException(otherList);
        if (arrayList.length != otherList.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        checkNullPointerException();
        return arrayList.length;
    }

    @Override
    public boolean isEmpty() {
        return arrayList == null;
    }

    @Override
    public void clear() {
        arrayList = null;
        size = 1;
    }

    @Override
    public int[] toArray() {
        checkNullPointerException();
        int[] newIntegerArray = new int[arrayList.length];
        for (int i = 0; i < newIntegerArray.length; i++) {
            newIntegerArray[i] = arrayList[i];
        }
        return newIntegerArray;
    }

    private void checkIllegalArgumentException(IntegerList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("При вызове метода параметр имеет значение null");
        }
    }

    private void checkArrayIndexOutOfBoundsException(int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Параметр индекса выходит за пределы фактического количества элементов или массива");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Параметр индекса имеет недопустимое значение");
        }
    }

    private void checkNullPointerException() {
        if (arrayList == null) {
            throw new NullPointerException("Список не существует");
        }
    }

    @Override
    public void generateRandomArrayList(int items) {
        java.util.Random random = new java.util.Random();
        arrayList = new int[items];
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = random.nextInt(100);
        }
    }

    @Override
    public void measureTimeOfBubbleSortMethod() {
        int[] newArray = Arrays.copyOf(arrayList, arrayList.length);
        long start = System.currentTimeMillis();
        sortBubble(newArray);
        System.out.println("Время сортировки списка методом пузырька = " + (System.currentTimeMillis() - start) + " мс");
    }

    @Override
    public void measureTimeOfSelectionSortMethod() {
        int[] newArray = Arrays.copyOf(arrayList, arrayList.length);
        long start = System.currentTimeMillis();
        sortSelection(newArray);
        System.out.println("Время сортировки списка методом выбора = " + (System.currentTimeMillis() - start) + " мс");
    }

    @Override
    public void measureTimeOfInsertionSortMethod() {
        int[] newArray = Arrays.copyOf(arrayList, arrayList.length);
        long start = System.currentTimeMillis();
        sortInsertion(newArray);
        System.out.println("Время сортировки списка методом вставки = " + (System.currentTimeMillis() - start) + " мс");
    }

    @Override
    public void measureTimeOfMergeSortMethod() {
        int[] newArray = Arrays.copyOf(arrayList, arrayList.length);
        long start = System.currentTimeMillis();
        mergeSort(newArray);
        System.out.println("Время сортировки списка методом слияния = " + (System.currentTimeMillis() - start) + " мс");
    }

    private void grow() {
        int newArraySize;
        if (arrayList.length % 2 == 0) {
            newArraySize = arrayList.length + (arrayList.length / 2);
        } else {
            newArraySize = arrayList.length + (arrayList.length / 2) + 1;
        }
        int[] extendedOriginalArray = Arrays.copyOf(arrayList, newArraySize);
        arrayList = extendedOriginalArray;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    //сортировка методом пузырька
    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    //сортировка методом выбора
    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    //сортировка методом вставки
    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    //сортировка слиянием
    private static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

}
