package com.sealedair.core.models;

import lombok.Getter;

import com.sealedair.utils.URLUtils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * Sling model representing the Link List component.
 */
@Getter
@Model(adaptables = {Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkListModel {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    @Default(values = "_self")
    private String linkBehavior;

    /** TRUE if linkBehavior is set to open in a new tab */
    private Boolean isLinkNewTab;

    @ValueMapValue
    private String ariaLabel;

    private String linkUrl;

    @PostConstruct
    private void init() {
        ResourceResolver resolver = resource.getResourceResolver();
        linkUrl = URLUtils.addHTMLIfPage(resolver, link);

        isLinkNewTab = URLUtils.isLinkNewTab(linkBehavior);
    }
}
