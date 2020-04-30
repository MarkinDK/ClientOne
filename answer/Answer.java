package answer;

import java.io.Serializable;

public class Answer implements Serializable {
    private String result;
    private static final long serialVersionUID = 6529685098267757690L;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
