package com.sealedair.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;

import lombok.Getter;

/**
 * Sling model for Navigation.
 */
@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationModel {

    public static final String PROP_HIDE_IN_NAV = "hideInNav";

    @ValueMapValue
    @Getter
    private String rootPath;

    @ValueMapValue
    @Getter
    private String userLoginPath;

    @ValueMapValue
    @Getter
    private String userLoginText;

    @Self
    private SlingHttpServletRequest request;

    /**
     * The variable topNavList
     */
    @Getter
    private List<TopNavigationModel> topNavList = Collections.emptyList();

    /**
     * Init Method of Navigation Model.
     */
    @PostConstruct
    protected final void init() {

        topNavList = new ArrayList<>();
        if(StringUtils.isNotBlank(rootPath)) {
            Resource resource = request.getResource();
            ResourceResolver resourceResolver = resource.getResourceResolver();
            Resource megaMenuResource = resourceResolver.getResource(rootPath);

            if(megaMenuResource == null) {
                return;
            }

            Page megaMenuPage = megaMenuResource.adaptTo(Page.class);
            if(megaMenuPage == null) {
                return;
            }
            Iterable<Page> iterable = () -> megaMenuPage.listChildren(new PageFilter());
            topNavList = StreamSupport.stream(iterable.spliterator(), false)
		            .filter(page -> !megaMenuPage.isHideInNav())
                    .map(Page::getContentResource)
                    .filter(Objects::nonNull)
                    .map(topPageContentResource -> topPageContentResource.adaptTo(TopNavigationModel.class))
                    .collect(Collectors.toList());
        }
    }
}
