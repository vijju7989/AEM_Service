package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(ContainerModel)
class ContainerSpec extends SiteSetupSpec {

    def "Container model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/content-container")
        def container = request.adaptTo(ContainerModel)

        then:
        container != null

        container.addContainer == false
        container.text == "Column Title Goes Here"
        container.type == "h4"
        container.alignment == "center"
        container.bgImagePosition == "bottom left"
        container.topPadding == "0"
        container.btmPadding == "25"
        container.rightPadding == "50"
        container.leftPadding == "100"
    }
}
