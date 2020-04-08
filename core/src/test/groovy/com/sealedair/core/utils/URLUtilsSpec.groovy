package com.sealedair.utils

import com.sealedair.core.SiteSetupSpec
import org.apache.sling.settings.SlingSettingsService
import spock.lang.Unroll

class URLUtilsSpec extends SiteSetupSpec {
    @Unroll
    def 'MakeRelativeURL returns the relative URL for internal pages'() {
        when:
        def actualResult = URLUtils.makeRelativeURL(resolver, mockExternalizer, request,
                aemContext.getService(SlingSettingsService), url)

        then:
        actualResult == expectedResult

        where:
        url                                 | expectedResult
        ''                                  | ''
        null                                | null
        'https://www.google.com'            | 'https://www.google.com'
        '/content/not/a/page'               | '/content/not/a/page'
    }
}
