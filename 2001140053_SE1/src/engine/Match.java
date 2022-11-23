package engine;

public class Match implements Comparable<Match> {
    private Word word;
    private Doc doc;
    private int frequency;
    public int firstIndex;
    public Match (Doc d , Word w , int freq, int firstIndex){
      this.doc = d;
      this.firstIndex=firstIndex;
      this.word=w;
      this.frequency= freq;
    }
    public int getFreq(){
        return this.frequency;
    }
    public int getFirstIndex(){
        return this.firstIndex;
    }
    public int compareTo(Match o){
        if(this.getFirstIndex()>o.getFirstIndex()){
            return 1;
        }else if (this.getFirstIndex()<o.getFirstIndex()){
            return -1;
        }
        return 0;
    }
    public Word getWord(){
        return this.word;
    }
}
