
package de.mytoys.mobile.mytoinder.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("colors")
    private List<Type> colors = new ArrayList<Type>();
    @SerializedName("style")
    private List<Type> style = new ArrayList<Type>();
    @SerializedName("type")
    private List<Type> type = new ArrayList<Type>();

    /**
     * 
     * @return
     *     The colors
     */
    public List<Type> getColors() {
        return colors;
    }

    /**
     * 
     * @param colors
     *     The colors
     */
    public void setColors(List<Type> colors) {
        this.colors = colors;
    }

    /**
     * 
     * @return
     *     The style
     */
    public List<Type> getStyle() {
        return style;
    }

    /**
     * 
     * @param style
     *     The style
     */
    public void setStyle(List<Type> style) {
        this.style = style;
    }

    /**
     * 
     * @return
     *     The type
     */
    public List<Type> getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(List<Type> type) {
        this.type = type;
    }

}
