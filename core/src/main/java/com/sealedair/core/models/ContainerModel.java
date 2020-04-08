package com.sealedair.core.models;

import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.commons.WCMUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.stream.Collectors;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContainerModel {

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    @Getter
    private boolean addContainer;

    @ValueMapValue
    @Getter
    private String text;

    @ValueMapValue
    @Getter
    private String type;

    @ValueMapValue
    @Getter
    @Default (values = "left")
    private String alignment;

    @ValueMapValue
    @Getter
    @Default (values = "center center")
    private String bgImagePosition;

    @ValueMapValue
    @Getter
    @Default (values = "0")
    private String topPadding;

    @ValueMapValue
    @Getter
    @Default (values = "0")
    private String btmPadding;

    @ValueMapValue
    @Getter
    @Default (values = "0")
    private String leftPadding;

    @ValueMapValue
    @Getter
    @Default (values = "0")
    private String rightPadding;

    @Inject
    @SlingObject
    private SlingHttpServletResponse response;

    public void includeEpilogForEditing() throws IOException, ServletException {
        ComponentContext cmpContext = WCMUtils.getComponentContext(request);
        WCMMode wcmMode = WCMMode.fromRequest(request);
        EditContext editContext = cmpContext.getEditContext();
        if (editContext == null) {
            return;
        }
        editContext.includeEpilog(request, response, wcmMode);
    }

    public String getCssClassNames() throws IOException, ServletException {
        ComponentContext cmpContext = WCMUtils.getComponentContext(request);
        return cmpContext.getCssClassNames()
            .stream().filter(name -> !name.equals("container")).collect(Collectors.joining(" "));
    }
}
