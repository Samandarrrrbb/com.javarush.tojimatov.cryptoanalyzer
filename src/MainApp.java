import java.io.IOException;
import java.util.Scanner;

    public class MainApp {

        private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
                'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

        public static void main(String[] args) {
            // Создаем экземпляры вспомогательных классов
            Cipher cipher = new Cipher();
            FileManager fileManager = new FileManager();
            Validator validator = new Validator();
            BruteForce bruteForce = new BruteForce();
            StatisticalAnalyzer analyzer = new StatisticalAnalyzer();

            Scanner scanner = new Scanner(System.in);

            // 1. Чтение зашифрованного текста из файла
            System.out.print("Введите путь к файлу с зашифрованным текстом: ");
            String filePath = scanner.nextLine();
            try {
                String encryptedText = fileManager.readFile(filePath);

                // 2. Запрашиваем у пользователя действие
                System.out.println("Выберите действие: ");
                System.out.println("1. Зашифровать текст");
                System.out.println("2. Расшифровать текст");
                System.out.println("3. Brute force");
                System.out.println("4. Статистический анализ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    // Зашифрование
                    System.out.print("Введите ключ: ");
                    int key = scanner.nextInt();
                    if (validator.isValidKey(key, ALPHABET)) {
                        String encrypted = cipher.encrypt(encryptedText, key);
                        System.out.println("Зашифрованный текст: " + encrypted);
                        fileManager.writeFile(encrypted, filePath);
                    } else {
                        System.out.println("Неверный ключ.");
                    }
                } else if (choice == 2) {
                    // Расшифрование
                    System.out.print("Введите ключ: ");
                    int key = scanner.nextInt();
                    if (validator.isValidKey(key, ALPHABET)) {
                        String decrypted = cipher.decrypt(encryptedText, key);
                        System.out.println("Расшифрованный текст: " + decrypted);
                        fileManager.writeFile(decrypted, filePath);
                    } else {
                        System.out.println("Неверный ключ.");
                    }
                } else if (choice == 3) {
                    // Brute force
                    String result = bruteForce.bruteForceDecrypt(encryptedText, ALPHABET);
                    System.out.println(result);
                } else if (choice == 4) {
                    // Статистический анализ
                    System.out.print("Введите образец текста для анализа: ");
                    scanner.nextLine();  // очистка буфера
                    String sampleText = scanner.nextLine();
                    int mostLikelyShift = analyzer.findMostLikelyShift(encryptedText, ALPHABET, sampleText);
                    System.out.println("Наиболее вероятный сдвиг: " + mostLikelyShift);
                    String decrypted = cipher.decrypt(encryptedText, mostLikelyShift);
                    System.out.println("Расшифрованный текст: " + decrypted);
                    fileManager.writeFile(decrypted, filePath);
                } else {
                    System.out.println("Неверный выбор.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения/записи файла: " + e.getMessage());
            }
        }
    }