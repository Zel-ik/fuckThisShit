import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Practicum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();

        List<String> words = readWordsFromFile(".idea/words.txt");



        if(words.size() < playersNumber){
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return;
        }
        // если слов меньше, чем участников, то выведите сообщение:
        // "Недостаточно слов в файле. Добавьте слова и обновите файл."
        // и завершите выполнение программы
        Collections.shuffle(words);
        // воспользуйтесь статическим методом Collections.shuffle(List<?> list),    
        // чтобы поменять порядок слов случайным образом

        int wordsNumber = words.size() / playersNumber;

        for (int i = 0; i < playersNumber; i++) {
            String filename = String.format("player%s.txt", i + 1);
            List<String> subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);

            writeListToFile(subList, filename);
        }

        System.out.println("Карточки готовы!");
    }

    private static List<String> readWordsFromFile(String filename) {
        List<String> fileWordsLine = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))){

            while(fileReader.ready()){
                fileWordsLine.add(fileReader.readLine());
            }

            return fileWordsLine;

        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }

        // добавьте построчное чтение из файла с помощью BufferedReader
        // в случае ошибки выведите сообщение: "Произошла ошибка во время чтения файла."
        return null;
    }

    private static void writeListToFile(List<String> list, String filename) {
        try(BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filename, StandardCharsets.UTF_8)))){
            for(String s : list){
                bufferedWriter.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        // добавьте запись слов в файл с помощью FileWriter
        // в случае ошибки выведите сообщение: "Произошла ошибка во время записи файла."
    }
}