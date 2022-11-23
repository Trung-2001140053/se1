package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Engine {
    List<Doc> document = new ArrayList<>();
    public int loadDocs(String dirname) {
        File f = new File(dirname);
        File[] OffFile = f.listFiles();

        int count = 0;
        for (File file: OffFile) {
            String s = "";

            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    s = s + sc.nextLine() + "\n";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Doc d = new Doc(s);
            document.add(d);
        }
        return OffFile.length;
    }

    public Doc[] getDocs() {
        Doc[] d = new Doc[this.document.size()];
        return this.document.toArray(d);
    }

    public List<Result> search(Query q) {
        List<Result> result = new ArrayList<Result>();
        Doc[] doc = getDocs();
        for (int i = 0 ; i < doc.length; i++) {
            List<Match> m;
            m = q.matchAgainst(doc[i]);
            if (m.size() <= 0) {
                break;
            }
            result.add(new Result(doc[i], m));
        }
        Collections.sort(result);
        return result;
    }

    public String htmlResult(List<Result> results) {
        String str = "";
        for (int i = 0; i < results.size(); i++){
            str = str + results.get(i).htmlHighlight();
        }

        return str;
    }
}