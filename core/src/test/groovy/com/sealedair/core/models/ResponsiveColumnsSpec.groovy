package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(ResponsiveColumnsModel)
class ResponsiveColumnsSpec extends SiteSetupSpec {

    def "Responsive Columns model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/responsive-columns")
        def respColumns = request.adaptTo(ResponsiveColumnsModel)

        then:
        respColumns != null

        respColumns.noGutters == false
        respColumns.revOrderMobile == true
        respColumns.colMatchHeight == true

        respColumns.columns != null
        respColumns.columns.size() == 2
        respColumns.columns.get(0).classes == "col-sm-8 col-sm-4"
        respColumns.columns.get(0).colWidth == "8"
        respColumns.columns.get(1).classes == "col-lg-12"
        respColumns.columns.get(1).colWidth == "4"
    }
}
