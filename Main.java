import java.util.Scanner;

public class Main {

    public int[] createTemporaryArray(char[] pattern){
        int[] temporaryArray = new int[pattern.length];
        int j = 0;
        for(int i = 1; i < pattern.length;){
            if (pattern[i] == pattern[j]){
                temporaryArray[i] = j + 1;
                j++;
                i++;
            } else {
                if (j != 0){
                    j = temporaryArray[j -1];
                } else {
                    temporaryArray[i] = 0;
                    i++;
                }
            }
        }

        return temporaryArray;
    }

    public boolean KMP(char[] pattern, char[] text){
        int[] temporaryArray = createTemporaryArray(pattern);
        int i = 0;
        int j = 0;

        while(i < text.length && j < pattern.length){
            if (pattern[j] == text[i]){
                i++;
                j++;
            } else {
                if (j == 0){
                    i++;
                } else {
                    j = temporaryArray[j-1];
                }
            }
        }

        return j == pattern.length;
    }

    public static void main(String[] args) {
        try(Scanner newScanner = new Scanner(System.in)){
            System.out.println("Enter text");
            String text = newScanner.nextLine();
            System.out.println("Enter pattern: ");
            String pattern = newScanner.nextLine();
            Main ss = new Main();
            System.out.println(ss.KMP(pattern.toCharArray(), text.toCharArray()));
        }
    }
}