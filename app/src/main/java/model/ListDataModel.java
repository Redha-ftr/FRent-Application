package model;

import java.util.List;

public class ListDataModel {
    private int code;
    private String message;
    private List<GedungModel> gedungModelList;

    public ListDataModel(int code, String message, List<GedungModel> gedungModelList) {
        this.code = code;
        this.message = message;
        this.gedungModelList = gedungModelList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GedungModel> getGedungModelList() {
        return gedungModelList;
    }

    public void setGedungModelList(List<GedungModel> gedungModelList) {
        this.gedungModelList = gedungModelList;
    }
}
