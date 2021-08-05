package com.google;

import java.util.Comparator;


public class SortByTitle implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
