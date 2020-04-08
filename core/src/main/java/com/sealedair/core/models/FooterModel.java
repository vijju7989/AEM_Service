package com.sealedair.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;
import java.util.List;
import com.sealedair.utils.DateUtils;

/**
 * Sling model representing the Footer component.
 */
@Getter
@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {
    private static final String COPYRIGHT = "Copyright";
    private static final String COPYRIGHT_SYMBOL = " \u00a9";
    private static final String BRAND_NAME = " Sealed Air";

    @ValueMapValue
    private String userLink;

    //@ChildResourceFromRequest
    @ChildResource
    private List<LinkListModel> portalLinks;

    //@ChildResourceFromRequest
    @ChildResource
    private List<LinkListModel> navLinks;

    //@ChildResourceFromRequest
    @ChildResource
    private List<LinkListModel> socialLinks;

    //@ChildResourceFromRequest
    @ChildResource
    private List<LinkListModel> utilLinks;

    public String copyright() {
        return (COPYRIGHT + COPYRIGHT_SYMBOL + DateUtils.getYear() + BRAND_NAME);
    }
}
