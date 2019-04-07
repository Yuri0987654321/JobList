public class List<T> {
    private Item<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Item<>();
            head.value = value;
        } else {
            Item i = head;
            while (i.next != null) {
                i = i.next;

            }
            i.next = new Item();
            i.next.value = value;
        }
    }

    public int size() {
        int size = 0;
        Item<T> temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    public T get(int index) {
        if (index < 0) {
            throw new RuntimeException("index must be greater then 0");
        }
        int number = 0;
        Item<T> temp = head;
        while (temp != null) {
            if (number == index) {
                return temp.value;
            }
            temp = temp.next;
            number++;
        }
        throw new RuntimeException("the number not found " + index);
    }

    public void methodPrintReverse(int size) {
        if (head == null || size() == 1) {
            //"Нечего инвертировать!
            return;
        }
        size--;
        if (size < 0) {
            return;
        }
        System.out.println(" " + get(size));
        methodPrintReverse(size);
    }

    public void delete(int index) {
        if (index < 0) {
            throw new RuntimeException("index должен быть больше 0");
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        Item<T> tmp = head;
        index--;
        while (tmp != null && index > 0) {
            tmp = tmp.next;
            index--;
        }
        if (index > 0) {
            throw new RuntimeException("index больше размера списка");
        }
        if (tmp != null && tmp.next != null) {
            tmp.next = tmp.next.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Item<T> tmp = head;
        sb.append("[");
        while (tmp != null) {
            if (sb.length() != 1) {
                sb.append(", ");
            }
            sb.append(tmp.value);
            tmp = tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void insert(int index, T value) {
        if (index < 0 || (head == null && index > 0)) {
            throw new RuntimeException("index меньше нуля или список пустой");
        }
        Item<T> newItem = new Item<>();
        newItem.value = value;
        if (index == 0) {
            newItem.next = head;
            head = newItem;
            return;
        }
        Item tmp = head;
        for (int i = 0; index - 1 > i && tmp != null; i++) {
            tmp = tmp.next;
        }
        if (tmp == null) {
            throw new RuntimeException("Error");
        }
        Item nextTmp = tmp.next; //сохранение ссылки на позицию, следующую после вставки
        tmp.next = new Item();
        tmp = tmp.next;
        tmp.value = value;
        tmp.next = nextTmp;
    }

    private static class Item<T> {
        T value;
        Item<T> next;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        List<String> list = new List<>();
        System.out.println("Init list");
        list.add("ноль");
        list.add("один");
        list.add("два");
        list.add("три");
        list.add("четыре");
        System.out.println("size=" + list.size());
        System.out.println(list.toString());
        System.out.println("Инвертированный список:");
        list.methodPrintReverse(list.size());
        System.out.println("_________________________________________________________________");
        System.out.println("Вставка:");
        list.insert(0, "Лилу0");
        System.out.println(list.toString());
        list.insert(2, "Лилу2");
        System.out.println(list.toString());
        list.insert(7, "Лилу7");
        System.out.println(list.toString());
        try {
            list.insert(10, "Лилу10");
        } catch (RuntimeException e) {
            System.out.println("Корректно  - не удалось добавить 10-й элемент");
        }
        System.out.println(list.toString());
        System.out.println("size=" + list.size());

        System.out.println("Удаление:");
        list.delete(0);
        System.out.println(list.toString());
        list.delete(2);
        System.out.println(list.toString());
        list.delete(1);
        System.out.println(list.toString());
        System.out.println("size=" + list.size());
    }
}


