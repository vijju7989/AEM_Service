import { seeDocumentReady } from '@dependencies/documentReady';
import Glide, { Controls, Autoplay, Swipe, Keyboard, Breakpoints, Images, Anchors} from 'node_modules/@glidejs/glide/dist/glide.modular.esm.js';

interface HeroCarouselInfo {
    glide;
}

export class HeroCarousel {
    private static readonly SEE_HERO_GLIDE_CLASS = 'see-hc__glide';

    private sliderInfos: HeroCarouselInfo[];

    constructor() {
        seeDocumentReady.ready(this.init);
    }

    /**
     * Initializes all slider components on the page.
     * Finds all tags with class see-glide and creates a settings object from the classes the tag contains
     * Then mounts the glide object and sets a listener on the window for the glide object to respond to
     * in case it is disabled for the specific viewports
     */
    private init = () => {
        const sliders = document.getElementsByClassName(HeroCarousel.SEE_HERO_GLIDE_CLASS);

        this.sliderInfos = [];

        for (let i = 0; i < sliders.length; i++) {
            const currSlider = sliders[i];

            const currGlide = new (Glide as any) (currSlider, {
                    type: 'carousel',
                    //autoplay: 5000,
                    perView: 1,
                    breakpoints: {
                        1920: {
                            //perView: 1,
                        },
                        1440: {
                            //perView: 1,
                        },
                        1280: {
                            //perView: 1,
                        },
                        1024: {
                            //perView: 1,
                        },
                        768: {
                            //perView: 1,
                        },
                        375: {
                            //perView: 1,
                        }
                    }
                });

            currGlide.mount({ Controls, Autoplay, Swipe, Keyboard, Breakpoints, Images, Anchors });

            const currSliderInfo = {glide: currGlide};
            this.sliderInfos.push(currSliderInfo);
        }
    }
}

export const heroCarousel = new HeroCarousel();
