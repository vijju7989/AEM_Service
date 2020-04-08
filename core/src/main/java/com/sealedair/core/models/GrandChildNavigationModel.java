package com.sealedair.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.sealedair.utils.PageUtils;
import com.sealedair.utils.URLUtils;

import lombok.Getter;


/**
 * Sling model for Grand Child Navigation Model.
 */
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GrandChildNavigationModel {

   
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
     * Init Method of Model.
     */
    @PostConstruct
    protected final void init() {
        if(resource.getParent() == null) {
            return;
        }    	
        Page topPage = resource.getParent().adaptTo(Page.class);
        linkLabel = PageUtils.getPageTitle(topPage);
        ResourceResolver resourceResolver = resource.getResourceResolver();
		linkUrl = URLUtils.addHTMLIfPage(resourceResolver, topPage.getPath());
    }  
}
