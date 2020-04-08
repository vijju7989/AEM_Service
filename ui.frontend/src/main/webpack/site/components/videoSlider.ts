import { seeDocumentReady } from '@dependencies/documentReady';

interface VideoSliderInfo {
    videoSlider
}

export class VideoSlider {
    private static readonly SEE_VIDEO_SLIDER_CLASS = 'see-vs';
    private static readonly SEE_VIDEO_CONTENT_CLASS = '.see-vs__content';
    private static readonly SEE_VIDEO_TILE_CLASS = '.see-vs__tile';
    private static readonly SEE_VIDEO_BLOCK_CLASS = '.see-vs__videos';
    private static readonly SEE_BG_IMAGE_CLASS = '.see-vs__img-wrapper';
    private static readonly SEE_VIDEO_TILE_NUMBER_CLASS = '.see-vs__number';
    private static readonly SEE_VIDEO_TILE_TEXT_CLASS = '.see-vs__text';
    private static readonly SEE_VIDEO_TILE_ABSTRACT_CLASS = '.see-vs__abstract';
    private static readonly SEE_VIDEO_PLAYER_CLASS = '.see-vs__player';


    private videoSliders: VideoSliderInfo[] = [];
    private videos = [];

    constructor() {
        seeDocumentReady.ready(this.init);
    }

    /**
     * Initializes all video slider components on the page.
     * Finds all tags with class see-vs and creates a settings object from the classes the tag contains.
     */
    private init = () => {
        const sliders = document.getElementsByClassName(VideoSlider.SEE_VIDEO_SLIDER_CLASS);

        this.initTiles(sliders);
        this.initVideos(sliders);
    }

    private initTiles = (sliders) => {
        for (let i = 0; i < sliders.length; i++) {
            const currSlider = sliders[i];
            const tileBlock = currSlider.querySelector(VideoSlider.SEE_VIDEO_CONTENT_CLASS);
            const tileElems = tileBlock.querySelectorAll(VideoSlider.SEE_VIDEO_TILE_CLASS);
            const imageElems = tileBlock.querySelectorAll(VideoSlider.SEE_BG_IMAGE_CLASS);
            const abstractElems = tileBlock.querySelectorAll(VideoSlider.SEE_VIDEO_TILE_ABSTRACT_CLASS);

            // Set up all tiles and listeners
            for (let j = 0; j < tileElems.length; j++) {
                // Since numbers above titles are zero-based we need to index them by 1.
                const t = tileElems[j].querySelector(VideoSlider.SEE_VIDEO_TILE_NUMBER_CLASS);
                const n = "0" + (j + 1);
                t.innerHTML = n;

                // Tile enter listener
                tileElems[j].addEventListener('mouseenter', (e:MouseEvent) => {
                    e.preventDefault();

                    // Fade out tile background images
                    for(let bg of imageElems) {
                        bg.classList.add('hidden');
                    }

                    // Show borders around each tile while video is playing
                    for(let tile of tileElems) {
                        const t = tile.querySelector(VideoSlider.SEE_VIDEO_TILE_TEXT_CLASS);
                        t.classList.add('hasBorder');
                    }

                    // Show current tile's abstract text and show/play/unpause its background video
                    abstractElems[j].classList.remove('hidden');
                    this.videos[j].classList.remove('hidden');
                    this.videos[j].play();
                })

                // Tile exit listener
                tileElems[j].addEventListener('mouseleave', (e:MouseEvent) => {
                    e.preventDefault();

                    // Show tile background images
                    for(let bg of imageElems) {
                        bg.classList.remove('hidden');
                    }

                    // Remove tile borders
                    for(let tile of tileElems) {
                        const t = tile.querySelector(VideoSlider.SEE_VIDEO_TILE_TEXT_CLASS);
                        t.classList.remove('hasBorder');
                    }

                    abstractElems[j].classList.add('hidden');
                    this.videos[j].pause();
                    this.videos[j].classList.add('hidden');
                })
            }
        }
    }

    private initVideos = (sliders) => {
        for (let i = 0; i < sliders.length; i++) {
            const currSlider = sliders[i];

            const videoBlock = currSlider.querySelector(VideoSlider.SEE_VIDEO_BLOCK_CLASS);
            const videoPlayer = videoBlock.querySelectorAll(VideoSlider.SEE_VIDEO_PLAYER_CLASS);

            for (let j = 0; j < videoPlayer.length; j++) {
                this.videos.push(videoPlayer[j]);
            }

            const currSliderInfo = {videoSlider: currSlider};
            this.videoSliders.push(currSliderInfo);
        }
    }

}

export const videoSlider = new VideoSlider();
