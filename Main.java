import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Todo todo = new Todo(Todo.MIN_CAPACITY);

        Scanner sc = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {

            todo.printActionsHelp();
            System.out.print("Ваш выбор: ");
            String action = sc.nextLine();

            switch (action) {
                case ("0"):
                    isRunning = false;
                    break;
                case "1":
                    System.out.print("Введите название задачи: ");
                    try {
                        boolean ret = todo.add(sc.nextLine());
                        todo.printActionResult(ret, "Добавлено!", "Ошибка добавления!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    todo.print();
                    break;
                case "3":
                    System.out.print("Введите номер для удаления: ");
                    try {
                        int taskNum = Integer.parseInt(sc.nextLine());
                        boolean ret = todo.delete(taskNum);
                        todo.printActionResult(ret, "Удалено!", "Ошибка удаления!");
                    } catch (NumberFormatException e) {
                        System.out.println("Номер должен быть цифрой!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.print("Введите задачу для удаления: ");
                    try {
                        String task = sc.nextLine();
                        boolean ret = todo.delete(task);
                        todo.printActionResult(ret, "Удалено!", "Ошибка удаления!");
                    } catch (NotExistsTodoTaskException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.print("Введите ключевое слово для удаления: ");
                    String keyword = sc.nextLine();
                    int countDeleted = todo.deleteByKeyword(keyword);
                    String okMsg = String.format("Удалено дел: %d!", countDeleted);
                    todo.printActionResult(countDeleted > 0, okMsg, "Дела не найдены!");
                    break;
                default:
                    System.out.println("Неизвестная команда!");
            }
        }
        sc.close();
        System.out.println("Программа завершила работу!");
    }

}
