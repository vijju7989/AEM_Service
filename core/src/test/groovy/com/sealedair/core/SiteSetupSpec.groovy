package com.sealedair.core

import com.adobe.acs.commons.models.injectors.impl.AemObjectInjector
import com.day.cq.commons.Externalizer
import com.day.cq.search.Query
import com.day.cq.search.QueryBuilder
import com.day.cq.search.result.Hit
import com.day.cq.search.result.SearchResult
import com.day.cq.wcm.api.PageManager
import io.wcm.testing.mock.aem.junit.AemContext
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.servlethelpers.MockSlingHttpServletRequest
import org.apache.sling.settings.SlingSettingsService
import org.apache.sling.testing.mock.sling.services.MockSlingSettingService
import org.junit.Rule
import spock.lang.Specification

class SiteSetupSpec extends Specification {

    @Rule
    AemContext aemContext = new AemContext()
    MockSlingHttpServletRequest request
    PageManager pageManager
    ResourceResolver resolver
    Externalizer mockExternalizer

    def setup() {
        aemContext.load().json('/site-structure.json', '/content')
        aemContext.load().json('/dam-structure.json', '/content/dam')
        aemContext.load().json('/etc-structure.json', '/etc')
        aemContext.load().json('/app-structure.json', '/apps')
        aemContext.load().json('/conf-structure.json', '/conf/sealedAir')
        aemContext.load().json('/tag-structure.json', '/content/cq:tags')
        aemContext.addModelsForPackage("com.sealedair.core.models")
        request = aemContext.request()
        resolver = aemContext.resourceResolver()
        pageManager = aemContext.pageManager()
        aemContext.registerService(AemObjectInjector.class, new AemObjectInjector())
        mockExternalizer = Mock(Externalizer) {
            publishLink(_, _) >> {
                arguments -> "https://managemymoney.com" + arguments[1]
            }
            relativeLink(_, _) >> {
                arguments -> arguments[1]
            }
        }
        aemContext.registerService(Externalizer.class, mockExternalizer, null)
    }
}
