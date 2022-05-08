package pro.sky;

public class ArrayList implements StringList {
    private String[] arrayList;
    private int size = 1;

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения
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

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения
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

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        checkArrayIndexOutOfBoundsException(index);
        checkIllegalArgumentException(item);
        arrayList[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
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

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        checkArrayIndexOutOfBoundsException(index);
        String item = arrayList[index];
        size--;
        if (size == 0) {
            arrayList = null;
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

    // Проверка на существование элемента.
    // Вернуть true/false
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

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
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

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
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

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        checkArrayIndexOutOfBoundsException(index);
        return arrayList[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
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

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        checkNullPointerException();
        return arrayList.length;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return arrayList == null;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        arrayList = null;
        size = 1;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
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
