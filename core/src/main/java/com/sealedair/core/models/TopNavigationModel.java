package com.sealedair.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.sealedair.utils.PageUtils;
import com.sealedair.utils.URLUtils;

import lombok.Getter;


/**
 * Sling model for TopNavigationModel.
 */
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TopNavigationModel {

    @Self
    private Resource resource;    

    /**
     * The variable linkLabel.
     */

    @ValueMapValue
    @Getter
    private String linkLabel;

    /**
     * The variable linkUrl.
     */

    @ValueMapValue
    @Getter
    private String linkUrl;

    /**
     * The variable childNavigationList.
     */

    @Getter
    private List<ChildNavigationModel> childNavigationList = Collections.emptyList();
    
     

    /**
     * Init Method of Model.
     */
    @PostConstruct
    protected final void init() {
    	ResourceResolver resolver = resource.getResourceResolver();
        if(resource.getParent() == null) {
            return;
        }    	
        Page topPage = resource.getParent().adaptTo(Page.class);
        linkLabel = PageUtils.getPageTitle(topPage);
        linkUrl = URLUtils.addHTMLIfPage(resolver, topPage.getPath());

        childNavigationList = new ArrayList<>();

        if (topPage != null) {
		    Iterable<Page> iterable = () -> topPage.listChildren(new PageFilter());
		    childNavigationList = StreamSupport.stream(iterable.spliterator(), false)
		            .filter(page -> !topPage.isHideInNav())
		            .map(Page::getContentResource)		            
		            .map(topPageContentResource -> topPageContentResource.
		                    adaptTo(ChildNavigationModel.class))
		            .collect(Collectors.toList());;
		}

    }
}
