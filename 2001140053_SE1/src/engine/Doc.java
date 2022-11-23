package engine;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title = new ArrayList<>();
    private List<Word> body = new ArrayList<>();

    public Doc(String content) {
        String[] Text = content.split("\n");
        String[] TitleWords = Text[0].split(" ");
        String[] BodyWords = Text[1].split(" ");

        for (int a = 0; a < BodyWords.length; a++) { 
            body.add(Word.createWord(BodyWords[a]));
        }
        for (int i = 0; i < TitleWords.length; i++) {
            title.add(Word.createWord(TitleWords[i]));
        }
    }
    public List<Word> getTitle() {
        return this.title;
    }

    public List<Word> getBody() {
        return this.body;
    }

    public boolean equals(Object o) {
        Doc doc = (Doc) o;
        boolean a = true;
        boolean b = true;
        for (int i = 0; i < title.size(); i++) {
            if (this.title.get(i).equals(doc.getTitle().get(i))){
                a = true;
            } else {
                a = false;
            }
        }
        for (int i = 0; i < body.size(); i++) {
            if (this.body.get(i).equals(doc.getBody().get(i))){
                b = true;
            } else {
                b = false;
            }
        }

        if ((a && b)) {
            return true;
        }
        return false;
    }
}
