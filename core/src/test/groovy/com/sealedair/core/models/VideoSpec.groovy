package com.sealedair.core.models

import com.sealedair.core.SiteSetupSpec
import spock.lang.Subject

@Subject(VideoModel)
class VideoSpec extends SiteSetupSpec {

    def "Video model is correctly initalized"() {
        when:
        aemContext.currentResource("/content/sealedair/language-masters/en/jcr:content/root/video")
        def video = request.adaptTo(VideoModel)

        then:
        video != null

        video.videoUrl == "/content/dam/sealedair/EQU_VS9X_VID_PROC_EMEA_EN14.mp4"
        video.posterUrl == "/content/dam/sealedair/haydn-golden-wcvuv00UDd4-unsplash@2x.jpg"
    }
}
