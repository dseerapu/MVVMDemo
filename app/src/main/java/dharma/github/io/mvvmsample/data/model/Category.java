package dharma.github.io.mvvmsample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 */
public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("photo_count")
    @Expose
    private Integer photoCount;
    @SerializedName("links")
    @Expose
    private CategoryLinks links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public CategoryLinks getLinks() {
        return links;
    }

    public void setLinks(CategoryLinks links) {
        this.links = links;
    }

}
