import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void printHelp() {
        System.out.println("""
                \nВыберите операцию:
                0. Выход из программы
                1. Добавить дело
                2. Показать дела
                3. Удалить дело по номеру
                4. Удалить дело по названию
                5. Удалить по ключевому слову
                """);
    }

    public static void printList(List<String> list) {
        System.out.println("Ваш список дел:");
        if (list.isEmpty()) {
            System.out.println("<Пусто>");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    public static boolean removeFromList(List<String> list, String taskName) {
        if (list.contains(taskName)) {
            List<String> toDeleteList = new ArrayList<>();
            for (String elem : list) {
                if (elem.equals(taskName)) {
                    toDeleteList.add(elem);
                }
            }
            return list.removeAll(toDeleteList);
        } else {
            return false;
        }
    }

    public static boolean removeByKeyword(List<String> list, String keyword) {
            List<String> toSaveList = new ArrayList<>();
            for (String elem : list) {
                if (!elem.contains(keyword)) {
                    toSaveList.add(elem);
                }
            }
            return list.retainAll(toSaveList);
    }

    public static void main(String[] args) {

        List<String> todoList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        boolean isRunning = true;
        while(isRunning) {
            printHelp();
            System.out.print("Ваш выбор: ");
            String action = sc.nextLine();

            switch (action) {
                case ("0"):
                    isRunning = false;
                    break;
                case "1":
                    System.out.print("Введите название задачи: ");
                    todoList.add(sc.nextLine());
                    System.out.println("Добавлено!");
                    printList(todoList);
                    break;
                case "2":
                    printList(todoList);
                    break;
                case "3":
                    System.out.print("Введите номер для удаления: ");
                    int taskNum = Integer.parseInt(sc.nextLine());
                    if (!todoList.isEmpty() && taskNum <= todoList.size() && taskNum >= 0) {
                        todoList.remove(taskNum - 1);
                        System.out.println("Удалено!");
                        printList(todoList);
                    } else {
                        System.out.println("Ошибка удаления!");
                    }
                    break;
                case "4":
                    System.out.print("Введите задачу для удаления: ");
                    String taskName = sc.nextLine();
                    if (removeFromList(todoList, taskName)) {
                        System.out.println("Удалено!");
                        printList(todoList);
                    } else {
                        System.out.println("Ошибка удаления!");
                    }
                    break;
                case "5":
                    System.out.print("Введите ключевое слово: ");
                    String keyword = sc.nextLine();
                    if (removeByKeyword(todoList, keyword)) {
                        System.out.println("Удалено!");
                        printList(todoList);
                    } else {
                        System.out.println("Ошибка удаления!");
                    }
                    break;
                default:
                    System.out.println("Неизвестная команда!");
            }

        }

        sc.close();
        System.out.println("Программа завершила работу!");

    }
}



