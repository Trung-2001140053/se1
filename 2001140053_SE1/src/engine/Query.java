package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Query {
    List<Word> KeyWords = new ArrayList<>();

    public Query(String searchParse) {
        String[] s = searchParse.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (Word.createWord(s[i]).isKeyword()) {
                KeyWords.add(Word.createWord(s[i]));
            }
        }
    }

    public List<Word> getKeywords() {
        return KeyWords;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> list = new ArrayList<Match>();
        List<Word> Word = new ArrayList<Word>();
        for (List<Word> word: Arrays.asList(d.getTitle(), d.getBody()))  {
            Word.addAll(word);
        }
        for(int i = 0;i < KeyWords.size();) {
            Word word = KeyWords.get(i);
            int freq = 0;
            int firstFound = Word .indexOf(word);
            for (int j = 0; j < Word.size(); j++) {
                if (word.equals(Word.get(j))) {
                    freq =  freq + 1;
                }

                if (freq == 0) {
                    firstFound = j + 1;
                }
            }
            if (freq > 0) {
                list.add(new Match(d, word, freq, firstFound));
            }
            i++;
        }
        Collections.sort(list);
        return list;
    }
}
