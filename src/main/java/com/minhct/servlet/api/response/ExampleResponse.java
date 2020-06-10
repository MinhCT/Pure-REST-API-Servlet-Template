package com.minhct.servlet.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ExampleResponse {
    @SerializedName("pageId")
    private Integer id;

    @SerializedName("pageViewCount")
    private Integer viewCount;

    private List<String> listData;
    private Map<String, String> mapData;
    private List<UserDetail> userDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public Map<String, String> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public static class UserDetail {
        private String fullName;
        private String lastActive;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getLastActive() {
            return lastActive;
        }

        public void setLastActive(String lastActive) {
            this.lastActive = lastActive;
        }
    }
}
