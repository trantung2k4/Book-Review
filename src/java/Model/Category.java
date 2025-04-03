package Model;

import java.util.List;

public class Category {

    private int cId;
    private String cName;
    private List<Sub> subCate;
    private String image;

    public Category(int cId, String cName) {

        this.cId = cId;
        this.cName = cName;
    }

    public Category(int cId, String cName, String image) {
        this.cId = cId;
        this.cName = cName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<Sub> getSubCate() {
        return subCate;
    }

    public void setSubCate(List<Sub> subCate) {
        this.subCate = subCate;
    }

    public String toString() {
        return "Category [cId=" + cId + ", cName=" + cName + "]";
    }
}
