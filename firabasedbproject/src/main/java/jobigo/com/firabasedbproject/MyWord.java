package jobigo.com.firabasedbproject;

/**
 * Created by Murager on 4/6/17.
 */

public class MyWord {

    private String id;

    private String word;

    public MyWord() {
    }

    public MyWord(String id, String word) {
        this.id = id;
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyWord{");
        sb.append("id='").append(id).append('\'');
        sb.append(", word='").append(word).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
