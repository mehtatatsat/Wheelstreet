package com.tatsat.wheelstreet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Questions implements Serializable{


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("dataType")
        @Expose
        private String dataType;

        private String answerQ;
        private String givenANs;
        private String ansShown;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getAnswerQ() {
            return answerQ;
        }

        public void setAnswerQ(String answerQ) {
            this.answerQ = answerQ;
        }

        public String getGivenANs() {
            return givenANs;
        }

        public void setGivenANs(String givenANs) {
            this.givenANs = givenANs;
        }

        public String getAnsShown() {
            return ansShown;
        }

        public void setAnsShown(String ansShown) {
            this.ansShown = ansShown;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"id=\":\"" + id +'\"' +
                    ",\"answer\":\"" + givenANs + '\"' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                '}';
    }
}
