package com.frikiteam.events.resource;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SaveEventInformationResource {
    private String title;
    private String description;
    private String image;
}
