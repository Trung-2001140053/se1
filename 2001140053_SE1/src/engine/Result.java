package engine;

import java.util.ArrayList;
import java.util.List;

public class Result implements Comparable<Result> {
    private Doc doc;
    private List<Match> ListMatch;

    public Result(Doc d, List<Match> matches) {
        this.doc = d;
        this.ListMatch = matches;
    }

    public List<Match> getMatches() {
        return this.ListMatch;
    }

    public int getTotalFrequency() {
        int a = 0;
        int b = 0;
        while (b < ListMatch.size()) {
            a = a + ListMatch.get(b).getFreq();
            b++;
        }
        return a;
    }

    public int getAverageFirstIndex() {
        int c = 0;
        int d = 0;
        while (d < ListMatch.size()) {
             c= c + ListMatch.get(d).getFirstIndex();
            d++;
        }
        return c / ListMatch.size();
    }

    public String htmlHighlight() {

        List<Word> wordTitle = new ArrayList<>(doc.getTitle());
        String title = "";
        int a = 0;
        for (int i = 0; i < wordTitle.size(); i++) {
            for (int j = 0; j < ListMatch.size(); j++) {
                if (ListMatch.get(j).getWord().equals(wordTitle.get(i))) {
                    title = title + wordTitle.get(i).getPrefix() + "<u>" +
                            wordTitle.get(i).getText() + "</u>" + wordTitle.get(i).getSuffix() +" ";
                    a++;
                }
            }
            if(a > 0){
                a = 0;
                continue;
            }
            title = title + wordTitle.get(i) + " ";
        }
        title = "<h3>" + title.trim() + "</h3>";

        // body
        List<Word> wordBody = new ArrayList<>(doc.getBody());
        String body = "";
        for (int i = 0; i < wordBody.size(); i++) {
            for (int j = 0; j < ListMatch.size(); j++) {
                if (ListMatch.get(j).getWord().equals(wordBody.get(i))) {
                    body = body + wordBody.get(i).getPrefix() + "<b>" +
                            wordBody.get(i).getText() + "</b>" + wordBody.get(i).getSuffix() +" ";
                    a++;
                }
            }
            if(a> 0){
                a =0;
                continue;
            }
            body = body + wordBody.get(i) + " ";
        }
        body = "<p>" + body.trim()  + "</p>";
        String str = title + body;
        return str;
    }

    public int compareTo(Result o) {
        int Size = this.getMatches().size();
        int Freq = this.getTotalFrequency();
        int FirstIndex = this.getAverageFirstIndex();
        int oSize = o.getMatches().size();
        int oFreq = o.getTotalFrequency();
        int oFirstIndex = o.getAverageFirstIndex();
        if (Size<oSize) {
            return 1;
        } else if (Size == oSize) {
            if (Freq < oFreq) {
                return 1;
            } else if ( Freq== oFreq) {
                if (FirstIndex == oFirstIndex) {
                    return 0;
                } else if (FirstIndex < oFirstIndex) {
                    return 1;
                }
            }
        }

        return -1;
    }
    public Doc getDoc() {
        return this.doc;
    }
}