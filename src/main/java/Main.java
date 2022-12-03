import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Runnable palindromic = () -> findPalindromic(texts);
        Runnable sameLetters = () -> findSameLetters(texts);
        Runnable ascendingLetters = () -> findAscendingLetters(texts);

        Thread t1 = new Thread(palindromic);
        Thread t2 = new Thread(sameLetters);
        Thread t3 = new Thread(ascendingLetters);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + counterThree + " шт.");
        System.out.println("Красивых слов с длиной 4: " + counterFour + " шт.");
        System.out.println("Красивых слов с длиной 5: " + counterFive + " шт.");

    }

    static AtomicInteger counterThree = new AtomicInteger();
    static AtomicInteger counterFour = new AtomicInteger();
    static AtomicInteger counterFive = new AtomicInteger();

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void findPalindromic(String[] texts) {
        for (String word : texts) {
            switch (word.length()) {
                case 3:
                    if (word.charAt(0) == word.charAt(2) && word.charAt(1) != word.charAt(2)) {
                        counterThree.getAndIncrement();
                    }

                    break;
                case 4:
                    if (word.charAt(0) == word.charAt(3) && word.charAt(1) == word.charAt(2) &&
                            word.charAt(0) != word.charAt(1)) {
                        counterFour.getAndIncrement();
                    }
                    break;
                case 5:
                    if (word.charAt(1) == word.charAt(3) && word.charAt(0) == word.charAt(2) &&
                            word.charAt(2) == word.charAt(4) && word.charAt(0) != word.charAt(1) &&
                            word.charAt(3) != word.charAt(4) || word.charAt(0) == word.charAt(4) &&
                            word.charAt(1) == word.charAt(3) && word.charAt(1) != word.charAt(2)) {
                        counterFive.getAndIncrement();
                    }
                    break;
            }
        }
    }

    public static void findSameLetters(String[] texts) {
        for (String word : texts) {
            switch (word.length()) {
                case 3:
                    if (word.charAt(0) == word.charAt(1) && word.charAt(1) == word.charAt(2)) {
                        counterThree.getAndIncrement();
                    }
                    break;
                case 4:
                    if (word.charAt(0) == word.charAt(1) && word.charAt(1) == word.charAt(2) &&
                            word.charAt(2) == word.charAt(3)) {
                        counterFour.getAndIncrement();
                    }
                    break;
                case 5:
                    if (word.charAt(0) == word.charAt(1) && word.charAt(1) == word.charAt(2) &&
                            word.charAt(2) == word.charAt(3) && word.charAt(3) == word.charAt(4)) {
                        counterFive.getAndIncrement();
                    }
                    break;
            }
        }
    }

    public static void findAscendingLetters(String[] texts) {
        for (String word : texts) {
            switch (word.length()) {
                case 3:
                    if (word.charAt(0) <= word.charAt(1) && word.charAt(1) < word.charAt(2)) {
                        counterThree.getAndIncrement();
                    }
                    break;
                case 4:
                    if (word.charAt(0) <= word.charAt(1) && word.charAt(1) <= word.charAt(2) &&
                            word.charAt(2) < word.charAt(3)) {
                        counterFour.getAndIncrement();
                    }
                    break;
                case 5:
                    if (word.charAt(0) <= word.charAt(1) && word.charAt(1) <= word.charAt(2) &&
                            word.charAt(2) <= word.charAt(3) && word.charAt(3) < word.charAt(4)) {
                        counterFive.getAndIncrement();
                    }
                    break;
            }
        }
    }
}

