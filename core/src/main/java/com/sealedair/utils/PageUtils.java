package com.sealedair.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public final class PageUtils {

    private PageUtils() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Get the Page Property value.
     *
     * @param page The Page.
     * @param property The page property.
     * @return the page property.
     */

	public static String getPageProperty(final Page page, final String property) {
		if (page != null) {
			final ValueMap pageProperties = page.getProperties();
			if (pageProperties != null) {
				return pageProperties.get(property, StringUtils.EMPTY);
			}
		}
		return StringUtils.EMPTY;
	}

    /**
     * Get the Page Title.
     *
     * @param page The Page.
     * @return the page title.
     */

	public static String getPageTitle(final Page page) {
		if (page != null) {
			return StringUtils.defaultIfEmpty(page.getTitle(), page.getPageTitle());
		}
		return StringUtils.EMPTY;
	}

    /**
     * Get the Image Path for the page.
     *
     * @param page The Page.
     * @param property The page property.
     * @return the imagePath - the page image path.
     */

	public static String getImagePath(Page page, String property) {
		String imagePath = StringUtils.EMPTY;
		if (null != page) {
			Resource resourceImage = page.getContentResource().getChild("image");
			if (null != resourceImage) {
				ValueMap vmImage = resourceImage.adaptTo(ValueMap.class);
				if (vmImage.containsKey(property)) {
					imagePath = vmImage.get(property, String.class);
				}
			}
		}
		return imagePath;
	}

}
