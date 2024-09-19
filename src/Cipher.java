public class Cipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    private int findIndexInAlphabet(char symbol) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == symbol) {
                return i;
            }
        }
        return -1;

    }

    public String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();       //(index + key): К текущему индексу символа прибавляется сдвиг (ключ).
        for (char symbol : text.toCharArray()) {         // Это и есть операция шифрования.
            int index = findIndexInAlphabet(symbol);    //% ALPHABET.length: Используем оператор остатка от деления, чтобы новый индекс не выходил за границы алфавита.
            if (index != -1) {                         //Например, если алфавит состоит из 26 букв и мы сдвигаем букву ‘Z’ (индекс 25) на 1, новый индекс будет (25 + 1) % 26 = 0,
                int newindex = (index + key) % ALPHABET.length;//то есть буква перейдёт на ‘A’.
                result.append(ALPHABET[newindex]);   //ALPHABET.length: Это длина массива алфавита, который ты используешь для шифрования.
            } else {
                result.append(symbol);// Если не нашли его то просто добавляем
            }
        }
        return result.toString();
    }
    public  String decrypt(String text,int key){
        return encrypt(text, ALPHABET.length-key);// Тут мы отнимаем на сколько по ключу и используем готовый метод encrypt
    }

}
