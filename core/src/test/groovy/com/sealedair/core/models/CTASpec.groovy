package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(CTAModel)
class CTASpec extends SiteSetupSpec {

    def "CTA model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/cta")
        def cta = request.adaptTo(CTAModel)

        then:
        cta != null

        cta.style == "primary"
        cta.id == "CTA-3454"
        cta.text == "See Our Impact"
        cta.link == "/content/sealedair/language-masters/en"
        cta.alignment == "center"
        cta.hasArrow == true
        cta.accessibilityLabel == "View Impact Study"
        cta.linkBehavior == "_blank"
        cta.linkUrl == "/content/sealedair/language-masters/en.html"
    }
}
