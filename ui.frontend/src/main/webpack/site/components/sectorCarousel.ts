import { seeDocumentReady } from '@dependencies/documentReady';
import Glide, { Controls, Autoplay, Swipe, Keyboard, Breakpoints, Images, Anchors} from 'node_modules/@glidejs/glide/dist/glide.modular.esm.js';

interface SectorCarouselInfo {
    glide;
}

export class SectorCarousel {
    private static readonly SEE_GLIDE_CLASS = 'see-sector__glide';

    private sliderInfos: SectorCarouselInfo[];

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
        const sliders = document.getElementsByClassName(SectorCarousel.SEE_GLIDE_CLASS);

        this.sliderInfos = [];

        for (let i = 0; i < sliders.length; i++) {
            const currSlider = sliders[i];

            const currGlide = new (Glide as any) (currSlider, {
                    type: 'carousel',
                   // autoplay: 5000,
                    perView: 1,
                    gap: 150,
                    peek: {
                        before: 600,
                        after: 600,
                    },
                    breakpoints: {
                        1700: {
                            perView: 1,
                            gap: 120,
                            peek: {
                                before: 600,
                                after: 500,
                            }
                        },
                        1600: {
                            perView: 1,
                            gap: 120,
                            peek: {
                                before: 500,
                                after: 500,
                            }
                        },
                        1500: {
                            perView: 1,
                            gap: 100,
                            peek: {
                                before: 400,
                                after: 400,
                            }
                        },
                        1400: {
                            perView: 1,
                            gap: 100,
                            peek: {
                                before: 400,
                                after: 400,
                            }
                        },
                        1300: {
                            perView: 1,
                            gap: 120,
                            peek: {
                                before: 380,
                                after: 380,
                            }
                        },
                        1200: {
                            perView: 1,
                            gap: 180,
                            peek: {
                                before: 380,
                                after: 380,
                            }
                        },
                        1100: {
                            perView: 1,
                            gap: 100,
                            peek: {
                                before: 300,
                                after: 300,
                            }
                        },
                        1000: {
                            perView: 1,
                            gap: 100,
                            peek: {
                                before: 250,
                                after: 250,
                            }
                        },
                        900: {
                            perView: 1,
                            gap: 150,
                            peek: {
                                before: 250,
                                after: 250,
                            }
                        },
                        800: {
                            perView: 1,
                            gap: 170,
                            peek: {
                                before: 250,
                                after: 250,
                            }
                        },
                        700: {
                            perView: 1,
                            gap: 90,
                            peek: {
                                before: 150,
                                after: 150,
                            }
                        },
                        600: {
                            perView: 1,
                            gap: 60,
                            peek: {
                                before: 100,
                                after: 100,
                            }
                        },
                        500: {
                            perView: 1,
                            gap: 55,
                            peek: {
                                before: 95,
                                after: 95,
                            }
                        },
                        400: {
                            perView: 1,
                            gap: 150,
                            peek: {
                                before: 70,
                                after: 70,
                            }
                        },
                    }
                });

            currGlide.mount({ Controls, Autoplay, Swipe, Keyboard, Breakpoints, Images, Anchors });

            const currSliderInfo = {glide: currGlide};
            this.sliderInfos.push(currSliderInfo);
        }
    }
}

export const sectorCarousel = new SectorCarousel();
