package darin.com.dwclientservice.model;

/**
 * Created by Darin on 7/7/2016.
 */
public class RegisData {
    private String id;
    private String name;


    public RegisData(String name) {
        this.name = name;
    }

    public RegisData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
