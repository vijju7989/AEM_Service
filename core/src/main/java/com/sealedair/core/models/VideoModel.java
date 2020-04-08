package com.sealedair.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;

/**
 *
 * Sling model for the Video Component
 *
 * */
@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoModel {
    @ValueMapValue
    @Getter
    private String videoUrl;

    @ValueMapValue
    @Getter
    private String posterUrl;
}
