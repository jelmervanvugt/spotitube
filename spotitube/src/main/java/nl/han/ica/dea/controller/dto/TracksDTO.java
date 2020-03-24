package nl.han.ica.dea.controller.dto;

import java.util.ArrayList;

public class TracksDTO {

    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    public TracksDTO() {}

    public TracksDTO(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

}
