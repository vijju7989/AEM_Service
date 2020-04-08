package com.sealedair.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;

import java.util.List;

/**
 * Sling Model used to retrieve the values set
 * from the Responsive Column component dialog.
 */
@Getter
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResponsiveColumnsModel {

    @ValueMapValue
    private boolean noGutters;
    @ValueMapValue
    private boolean revOrderMobile;
    @ValueMapValue
    private boolean colMatchHeight;

    @ChildResource
    public List<ResponsiveColumnItem> columns;

    /**
     * Sling Model to store each Responsive Column
     * object in the multifield for Responsive Column Model.
     */
    @Getter
    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class ResponsiveColumnItem {

        @ValueMapValue
        private String classes;
        @ValueMapValue
        private String colWidth;
    }
}
