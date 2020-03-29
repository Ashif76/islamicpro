package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class DashBoardDuaItem {
        private int id;
        @SerializedName("category")
        private String categoryName;
        @SerializedName("no_of_chapter")
        private int noOfChapter;
        @SerializedName("icon_name")
        private String iconName;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getNoOfChapter() {
            return noOfChapter;
        }

        public void setNoOfChapter(int noOfChapter) {
            this.noOfChapter = noOfChapter;
        }

        public String getIconName() {
            return iconName;
        }

        public void setIconName(String iconName) {
            this.iconName = iconName;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return categoryName;
        }

        public void setName(String name) {
            this.categoryName = name;
        }
    }

