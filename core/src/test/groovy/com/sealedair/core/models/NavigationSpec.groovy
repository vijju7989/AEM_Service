 package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(NavigationModel)
class NavigationSpec extends SiteSetupSpec {

    def "Navigation model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/header")
        def navigation = request.adaptTo(NavigationModel)

        then:
        navigation != null

        navigation.rootPath == "/content/sealedair/us/en"
        navigation.userLoginText == "My Account"
        navigation.userLoginPath == "https://sealedair.com/signin"

        then:
        navigation != null
        navigation.topNavList.size() > 0

        navigation.topNavList.get(0).linkLabel == "Arctic Surfing In Lofoten"

        navigation.topNavList.childNavigationList.size() > 0

        navigation.topNavList.get(0).childNavigationList.get(0).linkLabel == "Arctic Surfing In Lofoten1"

        navigation.topNavList.childNavigationList.grandChildNavigationList.size() > 0

        navigation.topNavList.get(0).childNavigationList.get(0).grandChildNavigationList.get(0).linkLabel == "Arctic Surfing In Lofoten2"
    }
}
