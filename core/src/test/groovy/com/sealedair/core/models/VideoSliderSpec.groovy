package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(VideoSliderModel)
class VideoSliderSpec extends SiteSetupSpec {

    def "Video Slider model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/videoslider")
        def vsModel = request.adaptTo(VideoSliderModel)

        then:
        vsModel != null

        // VIDEO SLIDES
        vsModel.slides != null
        vsModel.slides.size() == 2
        vsModel.slides.get(0).title == "Investor Relations"
        vsModel.slides.get(0).text == "Lorem Ipsum Dolor"
        vsModel.slides.get(0).desktopImageUrl == "/content/dam/sealedair/ScreenShot2020-01-22at2.19.30PM.jpg"
        vsModel.slides.get(0).mobileTabletImageUrl == "/content/dam/sealedair/ScreenShot2020-01-22at2.19.30PM.jpg"
        vsModel.slides.get(0).videoUrl == "/content/dam/sealedair/EQU_VS9X_VID_PROC_EMEA_EN14.mp4"
        vsModel.slides.get(0).linkPath == "/content/sealedair/us/en/arctic-surfing-in-lofoten"
        vsModel.slides.get(0).linkUrl == "/content/sealedair/us/en/arctic-surfing-in-lofoten.html"

        vsModel.slides.get(1).linkUrl == "https://www.sealedair.com"
    }
}
