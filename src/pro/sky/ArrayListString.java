package pro.sky;

public class ArrayListString implements StringList {
    private String[] arrayList;
    private int size = 1;

    @Override
    public String add(String item) {
        checkIllegalArgumentException(item);
        if (isEmpty()) {
            arrayList = new String[size];
            arrayList[size - 1] = item;
        } else {
            size++;
            String[] newArrayList = new String[size];
            for (int i = 0; i < arrayList.length; i++) {
                newArrayList[i] = arrayList[i];
            }
            newArrayList[size - 1] = item;
            arrayList = newArrayList;
        }
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkArrayIndexOutOfBoundsException(index);
        checkIllegalArgumentException(item);
        checkNullPointerException();
        size++;
        String[] newArrayList = new String[size];
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
    public String set(int index, String item) {
        checkArrayIndexOutOfBoundsException(index);
        checkIllegalArgumentException(item);
        arrayList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkIllegalArgumentException(item);
        int count = 0;
        for (String s : arrayList) {
            if (s.equals(item)) {
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
        String[] newArrayList = new String[size];
        for (String s : arrayList) {
            if (!s.equals(item)) {
                newArrayList[count] = s;
                count++;
            }
        }
        arrayList = newArrayList;
        return item;
    }

    @Override
    public String remove(int index) {
        checkArrayIndexOutOfBoundsException(index);
        String item = arrayList[index];
        size--;
        if (size == 0) {
            arrayList = null;
            size = 1;
            return item;
        }
        String[] newArrayList = new String[size];
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
    public boolean contains(String item) {
        checkIllegalArgumentException(item);
        for (String s : arrayList) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        checkIllegalArgumentException(item);
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkIllegalArgumentException(item);
        for (int i = arrayList.length - 1; i >= 0; i--) {
            if (arrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkArrayIndexOutOfBoundsException(index);
        return arrayList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        checkIllegalArgumentException(otherList);
        if (arrayList.length != otherList.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i].equals(otherList.get(i)) == false) {
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
    public String[] toArray() {
        checkNullPointerException();
        String[] newStringArray = new String[arrayList.length];
        for (int i = 0; i < newStringArray.length; i++) {
            newStringArray[i] = arrayList[i];
        }
        return newStringArray;
    }

    public void checkIllegalArgumentException(String item) {
        if (item == null) {
            throw new IllegalArgumentException("При вызове метода параметр имеет значение null");
        }
    }

    public void checkIllegalArgumentException(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("При вызове метода параметр имеет значение null");
        }
    }

    public void checkArrayIndexOutOfBoundsException(int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Параметр индекса выходит за пределы фактического количества элементов или массива");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Параметр индекса имеет недопустимое значение");
        }
    }

    public void checkNullPointerException() {
        if (arrayList == null) {
            throw new NullPointerException("Список не существует");
        }
    }

}
