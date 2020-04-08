package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(FooterModel)
class FooterSpec extends SiteSetupSpec {

    def "Footer model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/footer")
        def footer = request.adaptTo(FooterModel)
        def year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR))

        then:
        footer != null

        footer.userLink == "https://www.sealedair.com"
        footer.copyright() == "Copyright ©" + year + " Sealed Air"

        // PORTAL LINKS
        footer.portalLinks != null
        footer.portalLinks.size() == 2
        footer.portalLinks.get(0).text == "Investor Relations"
        footer.portalLinks.get(0).link == "https://www.sealedair.com"
        footer.portalLinks.get(0).linkBehavior == "_blank"
        footer.portalLinks.get(0).isLinkNewTab == true
        footer.portalLinks.get(0).linkUrl == "https://www.sealedair.com"
        footer.portalLinks.get(1).text == "Careers"
        footer.portalLinks.get(1).link == "/content/sealedair/language-masters/en"
        footer.portalLinks.get(1).linkBehavior == "_self"
        footer.portalLinks.get(1).isLinkNewTab == false
        footer.portalLinks.get(1).linkUrl == "/content/sealedair/language-masters/en.html"

        // NAVLINKS (TOP NAV)
        footer.navLinks != null
        footer.navLinks.size() == 2
        footer.navLinks.get(0).text == "Sectors"
        footer.navLinks.get(0).link == "/content/sealedair/language-masters/en"
        footer.navLinks.get(0).linkBehavior == "_blank"
        footer.navLinks.get(0).isLinkNewTab == true
        footer.navLinks.get(0).linkUrl == "/content/sealedair/language-masters/en.html"
        footer.navLinks.get(1).text == "Company"
        footer.navLinks.get(1).link == "https://www.sealedair.com"
        footer.navLinks.get(1).linkBehavior == "_self"
        footer.navLinks.get(1).isLinkNewTab == false
        footer.navLinks.get(1).linkUrl == "https://www.sealedair.com"

        // SOCIAL LINKS
        footer.socialLinks != null
        footer.socialLinks.size() == 2
        footer.socialLinks.get(0).link == "https://www.facebook.com"
        footer.socialLinks.get(0).icon == "facebook"
        footer.socialLinks.get(0).linkBehavior == "_blank"
        footer.socialLinks.get(0).isLinkNewTab == true
        footer.socialLinks.get(0).ariaLabel == "Facebook Page"
        footer.socialLinks.get(0).linkUrl == "https://www.facebook.com"
        footer.socialLinks.get(1).link == "https://www.twitter.com"
        footer.socialLinks.get(1).icon == "twitter"
        footer.socialLinks.get(1).linkBehavior == "_self"
        footer.socialLinks.get(1).isLinkNewTab == false
        footer.socialLinks.get(1).ariaLabel == "Twitter Page"
        footer.socialLinks.get(1).linkUrl == "https://www.twitter.com"

        // UTILITY LINKS
        footer.utilLinks != null
        footer.utilLinks.size() == 2
        footer.utilLinks.get(0).text == "Privacy Policy"
        footer.utilLinks.get(0).link == "/content/sealedair/language-masters/en"
        footer.utilLinks.get(0).linkBehavior == "_self"
        footer.utilLinks.get(0).isLinkNewTab == false
        footer.utilLinks.get(0).linkUrl == "/content/sealedair/language-masters/en.html"
        footer.utilLinks.get(1).text == "Code of Conduct"
        footer.utilLinks.get(1).link == "/content/sealedair/language-masters/en"
        footer.utilLinks.get(1).linkBehavior == "_blank"
        footer.utilLinks.get(1).isLinkNewTab == true
        footer.utilLinks.get(1).linkUrl == "/content/sealedair/language-masters/en.html"
    }
}
