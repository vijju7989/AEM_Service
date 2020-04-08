package com.sealedair.core.models;

import javax.annotation.PostConstruct;

import com.sealedair.utils.URLUtils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;

/**
 * Sling model for the CTA
 */
@Getter
@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CTAModel {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    @Default(values = "primary")
    private String style;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    @Default(values = "left")
    private String alignment;

    @ValueMapValue
    private boolean hasArrow;

    @ValueMapValue
    private String accessibilityLabel;

    @ValueMapValue
    @Default(values = "_self")
    private String linkBehavior;

    private String linkUrl;

    /**
     * PostConstruct handles setting relative links properly.
     */
    @PostConstruct
    private void init() {
        ResourceResolver resolver = resource.getResourceResolver();
        linkUrl = URLUtils.addHTMLIfPage(resolver, link);
    }
}
