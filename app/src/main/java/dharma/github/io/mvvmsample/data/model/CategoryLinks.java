
package dharma.github.io.mvvmsample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 */
public class CategoryLinks {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("photos")
    @Expose
    private String photos;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

}
