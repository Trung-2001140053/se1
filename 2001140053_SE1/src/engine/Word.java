package engine;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    public static Set<String> stopWords = new LinkedHashSet<>();
    private String word;

    Word(String OGText) {
        this.word = OGText;
    }

    public boolean isKeyword() {
        if (stopWords.contains(this.word.toLowerCase())) {
            return false;
        } else {
            if (WordValidation()) {
                return true;
            }
            return false;
        }
    }

    public String getPrefix() {
        if (WordValidation()) {
            return this.word.substring(0, this.word.indexOf(getText()));
        }
        return "";
    }

    public String getSuffix() {
        if (WordValidation()) {
            int p = getPrefix().length();
            for (int i = p; i < this.word.length(); i++) {
                if (!Character.isLetterOrDigit(this.word.charAt(i))) {
                    if (this.word.charAt(i) == '-') {
                        continue;
                    }
                    return this.word.substring(i);
                }
            }
        }
        return "";
    }

    public String getText() {
        String str = this.word.replaceAll("('s)[\\W]*$", "");
        Pattern p = Pattern.compile("([-|']*[a-zA-Z]+[-|']*)");
        Matcher m = p.matcher(str);

        String s = "";
        while (m.find()) {
            s += m.group();
        }
        int length = s.length();
        return WordValidation() && length != 0 ? s : this.word;
    }

    public boolean equals(Object o) {
        Word word = (Word) o;
        return word.getText().toLowerCase().equals(getText().toLowerCase());
    }

    public String toString() {
        return this.word;
    }

    public static Word createWord(String RawText) {
        return new Word(RawText);
    }

    public boolean WordValidation() {
        if (this.word.length() <= 0) {
            return false;
        }
//        int a = 0;
        for (int i = 0; i < this.word.length(); i++) {
            if (String.valueOf(this.word.charAt(i)).matches(".*[\'\"].*")) {
                continue;
            }
            if (Character.isAlphabetic(this.word.charAt(i))) {
                continue;
            }
            if (Character.isDigit(this.word.charAt(i))) {
                return false;
            }
            if (this.word.charAt(i) == '-') {
                if (i - 1 < 0 || !Character.isAlphabetic(this.word.charAt(i - 1)) ||
                        i + 1 >= this.word.length() || !Character.isAlphabetic(this.word.charAt(i + 1))) {
                    return false;
                }
            } else if (i != this.word.length() - 1
                    && ((this.word.charAt(i) == '!'
                    || this.word.charAt(i) == '\"'
                    || this.word.charAt(i) == ':'
                    || this.word.charAt(i) == ' '
                    || this.word.charAt(i) == '.'
                    || this.word.charAt(i) == '?'
                    || this.word.charAt(i) == '-'
                    || this.word.charAt(i) == '\''
                    || this.word.charAt(i) == ','
                    || this.word.charAt(i) == ';'))) {
                return false;
            }
        }
        return true;
    }


    public static boolean loadStopWords(String fileName) {
        File f = new File(fileName);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String Line = sc.nextLine();
                stopWords.add(Line);
            }
            sc.close();
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
}
