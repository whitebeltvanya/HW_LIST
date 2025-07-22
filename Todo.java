import java.util.ArrayList;
import java.util.Iterator;

public class Todo {
    public static final int MIN_CAPACITY = 60;
    public static final int MAX_TASK_LENGTH = 100;
    private ArrayList<String> list;

    public Todo(int size) {
        this.list = new ArrayList<>(size);
    }

    public Todo() {
        this.list = new ArrayList<>();
    }

    public boolean add(String task) {
        if (task.length() > MAX_TASK_LENGTH)
            throw new IllegalArgumentException(String.format(
                    "Слишком длинное название задачи! Макс. значение: %d симв.", MAX_TASK_LENGTH));
        return list.add(task);
    }

    public void print() {
        System.out.println("Ваш список дел:");
        if (list.isEmpty()) {
            System.out.println("<Пусто>");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    public boolean delete(int taskNum) throws ArrayIndexOutOfBoundsException {
        if (taskNum <= 0 || taskNum > list.size())
            throw new ArrayIndexOutOfBoundsException("Такой номер задачи не существует!");
        return list.remove(taskNum - 1) != null;
    }

    public boolean delete(String task) throws NotExistsTodoTaskException {
        if (!list.contains(task)) {
            throw new NotExistsTodoTaskException("Такой задачи не существует!");
        }

        Iterator<String> it = list.iterator();
        int occurCount = 0;
        while (it.hasNext()) {
            String elem = it.next();
            if (elem.equals(task)) {
                it.remove();
                occurCount++;
            }
        }
        return occurCount > 0;
    }

    public int deleteByKeyword(String keyword) {
        Iterator<String> it = list.iterator();
        int occurCount = 0;
        while (it.hasNext()) {
            String elem = it.next();
            if (elem.contains(keyword)) {
                it.remove();
                occurCount++;
            }
        }
        return occurCount;
    }

    public void printActionsHelp() {

        System.out.println("""
                \nВыберите операцию:
                0. Выход из программы
                1. Добавить дело
                2. Показать дела
                3. Удалить дело по номеру
                4. Удалить дело по названию
                5. Удалить дело по ключевому слову
                """);
    }

    public void printActionResult(boolean isOk, String okMsg, String errMsg) {
        if (isOk) {
            System.out.println(okMsg);
            this.print();
        } else {
            System.out.println(errMsg);
        }
    }

}
