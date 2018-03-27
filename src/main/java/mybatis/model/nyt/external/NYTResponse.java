package mybatis.model.nyt.external;

public class NYTResponse {

    NYTDocs[] docs;
    NYTMeta meta;

    public NYTResponse() {
    }

    public NYTResponse(NYTDocs[] docs, NYTMeta meta) {
        this.docs = docs;
        this.meta = meta;
    }

    public NYTDocs[] getDocs() {
        return docs;
    }

    public void setDocs(NYTDocs[] docs) {
        this.docs = docs;
    }

    public NYTMeta getMeta() {
        return meta;
    }

    public void setMeta(NYTMeta meta) {
        this.meta = meta;
    }
}
