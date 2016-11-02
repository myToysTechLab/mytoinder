
package de.mytoys.mobile.mytoinder.model;

import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("questionOrder")
    private Integer questionOrder;
    @SerializedName("question")
    private String question;
    @SerializedName("yesFilter")
    private String yesFilter;
    @SerializedName("noFilter")
    private String noFilter;

    /**
     * 
     * @return
     *     The questionOrder
     */
    public Integer getQuestionOrder() {
        return questionOrder;
    }

    /**
     * 
     * @param questionOrder
     *     The questionOrder
     */
    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    /**
     * 
     * @return
     *     The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @return
     *     The yesFilter
     */
    public String getYesFilter() {
        return yesFilter;
    }

    /**
     * 
     * @param yesFilter
     *     The yesFilter
     */
    public void setYesFilter(String yesFilter) {
        this.yesFilter = yesFilter;
    }

    /**
     * 
     * @return
     *     The noFilter
     */
    public String getNoFilter() {
        return noFilter;
    }

    /**
     * 
     * @param noFilter
     *     The noFilter
     */
    public void setNoFilter(String noFilter) {
        this.noFilter = noFilter;
    }

}
