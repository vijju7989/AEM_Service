package com.sealedair.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
 * Sling model for ChildNavigationModel.
 */

@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChildNavigationModel {

	/**
	 * The Constant LOGGER.
	 */

	@Self
	@Getter
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

	@ValueMapValue
	@Getter
	private String linkImage;;

	/**
	 * The variable grandChildNavigationList.
	 */

	@Getter
	private List<GrandChildNavigationModel> grandChildNavigationList = Collections.emptyList();

	/**
	 * Init Method of Model.
	 */
	@PostConstruct
	protected final void init() {
        if(resource.getParent() == null) {
            return;
        }
		Page childPage = resource.getParent().adaptTo(Page.class);
		linkLabel = PageUtils.getPageTitle(childPage);
		linkImage = PageUtils.getImagePath(childPage, "fileReference");
		ResourceResolver resourceResolver = resource.getResourceResolver();
		linkUrl = URLUtils.addHTMLIfPage(resourceResolver, childPage.getPath());
		grandChildNavigationList = new ArrayList<>();

		if (childPage != null) {
			Iterable<Page> iterable = () -> childPage.listChildren(new PageFilter());
			grandChildNavigationList = StreamSupport.stream(iterable.spliterator(), false)
					.filter(page -> !childPage.isHideInNav()).map(Page::getContentResource).filter(Objects::nonNull)
					.map(childPageContentResource -> childPageContentResource.adaptTo(GrandChildNavigationModel.class))
					.collect(Collectors.toList());
		}

	}
}
