import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SelfChat {
    private static final String FILENAME = "self_chat.txt";
    private final Object lock = new Object();

    public void startChat() {
        try {
            // Удаляем файл, если он существует, чтобы очистить его содержимое
            File file = new File(FILENAME);
            file.delete();

            FileWriter writer = new FileWriter(FILENAME, false); // false означает, что файл будет перезаписан каждый раз при создании объекта FileWriter
            Scanner scanner1 = new Scanner(System.in);
            Scanner scanner2 = new Scanner(System.in);

            Thread thread1 = new Thread(() -> {
                while (true) {
                    String message = scanner1.nextLine();
                    synchronized (lock) {
                        try {
                            writer.write("Я 1: " + message + "\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                while (true) {
                    String message = scanner2.nextLine();
                    synchronized (lock) {
                        try {
                            writer.write("Я 2: " + message + "\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            thread1.start();
            thread2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}