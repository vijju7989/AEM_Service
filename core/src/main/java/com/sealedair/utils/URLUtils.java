package com.sealedair.utils;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.settings.SlingSettingsService;

import java.util.Set;

public final class URLUtils {

    public static final String LINK_TARGET_NEW_TAB = "_blank";
    public static final String LINK_TARGET_RELATIVE_SUFFIX = ".html";

    private URLUtils() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * If the specified URL points to a CQ Page, add the <code>.html</code> suffix to the URL.
     *
     * @param resourceResolver The ResourceResolver to resolve the URL.
     * @param url The URL to test if it links to a CQ Page.
     * @return the url, possibly updated to have <code>.html</code> appended to it.
     */
    public static String addHTMLIfPage(ResourceResolver resourceResolver, String url) {
        if (StringUtils.isEmpty(url) || resourceResolver == null) {
            return url;
        }

        Resource pageResource = resourceResolver.getResource(url);
        Page page = (pageResource != null && !ResourceUtil.isNonExistingResource(pageResource)) ?
            pageResource.adaptTo(Page.class) : null;

        if (page == null) {
            return url;
        }
        return url + LINK_TARGET_RELATIVE_SUFFIX;
    }

    /**
     * If the specified URL points to a AEM Page, format the page to use the relative URL
     * @param resourceResolver The ResourceResolver to resolve the URL.
     * @param externalizer The Externalizer to find the external URL
     * @param request The SlingHttpServletRequest to get the relative domain
     * @param url The URL to test if it links to a AEM Page.
     * @return the url, possibly updated to have <code>.html</code> appended to it.
     */
    public static String makeRelativeURL(ResourceResolver resourceResolver, Externalizer externalizer,
                                         SlingHttpServletRequest request, SlingSettingsService slingSettings, String url) {
        if (StringUtils.isEmpty(url) || resourceResolver == null || request == null) {
            return url;
        }

        Resource pageResource = resourceResolver.getResource(url);
        Page page = (pageResource != null && !ResourceUtil.isNonExistingResource(pageResource))
                ? pageResource.adaptTo(Page.class)
                : null;

        if (page == null) {
            return url;
        }
        Set<String> runModes = slingSettings.getRunModes();
        String relativeURL = externalizer.relativeLink(request, url);
        // Add .html for author to resolve internal author links and for local publish because local publish does not
        // have a dispatcher to go through.
        if (relativeURL != null && runModes.contains(Externalizer.AUTHOR)
                || (runModes.contains(Externalizer.PUBLISH) && runModes.contains(Externalizer.LOCAL))) {
            relativeURL += LINK_TARGET_RELATIVE_SUFFIX;
        }
        return relativeURL;
    }

    /**
     * Returns true if the linkBehavior is "_blank" meaning that
     * activating the link will open it in a new tab.
     * @param linkBehavior target of the link
     * @return true if linkBehavior is "_blank"
     */
    public static boolean isLinkNewTab(String linkBehavior) {
        return StringUtils.equals(linkBehavior, LINK_TARGET_NEW_TAB);
    }
}
