package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class used to represent a Video Library.
 */
class VideoLibrary {

  private final HashMap<String, Video> videos;

  private Map<String, String> flaggedVideos;

  VideoLibrary() {
    this.videos = new HashMap<>();
    flaggedVideos = new HashMap<>();
    try {
      File file = new File(this.getClass().getResource("/videos.txt").getFile());

      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] split = line.split("\\|");
        String title = split[0].strip();
        String id = split[1].strip();
        List<String> tags;
        if (split.length > 2) {
          tags = Arrays.stream(split[2].split(",")).map(String::strip).collect(
              Collectors.toList());
        } else {
          tags = new ArrayList<>();
        }
        this.videos.put(id, new Video(title, id, tags));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find videos.txt");
      e.printStackTrace();
    }
  }

  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  /**
   * Get a video by id. Returns null if the video is not found.
   */

  List<Video> getPlayableVideos() {
    List<Video> playableVideo = new ArrayList<>();
    for (String videoId: this.videos.keySet()) {
      if (!this.flaggedVideos.containsKey(videoId)) {
        playableVideo.add(this.videos.get(videoId));
      }
    }
    return playableVideo;
  }

  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }

  boolean isFlagged(String videoId) {
    return this.flaggedVideos.containsKey(videoId);
  }

  void allowVideo(String videoId) {
    this.flaggedVideos.remove(videoId);
  }

  void flagVideo(String videoId, String reason) {
    this.flaggedVideos.put(videoId, reason);
  }

  String getFlaggedReason(String videoId) {
    return this.flaggedVideos.get(videoId);
  }

}



