package com.github.listsapp.util.api;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

    public class GBook implements Serializable {

        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        private VolumeInfo volumeInfo;

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setVolumeInfo(VolumeInfo volumeInfo) {
            this.volumeInfo = volumeInfo;
        }


    }



