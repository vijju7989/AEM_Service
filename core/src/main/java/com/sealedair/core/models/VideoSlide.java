package com.sealedair.core.models;

import javax.annotation.PostConstruct;

import com.sealedair.utils.URLUtils;

import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model used to retrieve the values from a single Video Slide.
 */
@Getter
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoSlide {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String desktopImageUrl;

    @ValueMapValue
    private String mobileTabletImageUrl;

    @ValueMapValue
    private String videoUrl;

    @ValueMapValue
    private String linkPath;

    private String linkUrl;

    /**
     * Handles the appending of .html to internal/relative page paths.
     */
    @PostConstruct
    final void init() {
        ResourceResolver resolver = resource.getResourceResolver();
        linkUrl = URLUtils.addHTMLIfPage(resolver, linkPath);
    }
}
