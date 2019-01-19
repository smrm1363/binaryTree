package domain;

import java.io.Serializable;

public class NodeData {
    private String word;
    private Integer totalNumber;

    public NodeData(String word, Integer totalNumber) {
        this.word = word;
        this.totalNumber = totalNumber;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    @Override
    public String toString() {
        String result=" "+totalNumber.toString()+" ";
        if(word!=null)
            result = word+" "+result;
        return result;
    }
}
