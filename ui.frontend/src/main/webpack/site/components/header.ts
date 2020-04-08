import { seeDocumentReady } from '@dependencies/documentReady';

export class Header {
    private static readonly HEADER_CLASS = '.see-header';
    private static readonly NAVBAR_CLASS = '.see-header__navbar';
    private static readonly NAVBAR_BRAND = '.see-header__navbar-brand';
    private static readonly NAVBAR_TOGGLER = '.see-header__navbar-toggler';
    private static readonly HAMBURGER_BUTTON = '#seeHeaderToggleButton';
    private static readonly MENU_WRAPPER = '.see-header__top-menu';
    private static readonly TOP_MENU = '.see-header__top-menu--content';
    private static readonly SECOND_LEVEL_MENU = '.see-header__secondary-menu';
    private header: HTMLElement;
    private topLevelMenu: HTMLElement;

    constructor() {
        seeDocumentReady.ready(this.init);
    }

    /**
     * Initialize Header
     */
    private init = () => {
        this.header = document.querySelector(Header.HEADER_CLASS) as HTMLElement;

        this.initializeMobileMenuListener();
        this.scrollTop();
    }

    /**
     * Set up Mobile menu event listeners
     */
    private initializeMobileMenuListener = () => {
        this.header = document.querySelector(Header.HEADER_CLASS) as HTMLElement;
        if (this.header == null) {
            return;
        }

        const hamburgerButton = this.header.querySelector(Header.HAMBURGER_BUTTON) as HTMLElement;
        hamburgerButton.addEventListener("click", this.toggleMenu);

       this.addMainBtnListeners();
    }

    /**
     *
     */
    private scrollTop = () => {
        const header: HTMLElement = document.querySelector(Header.NAVBAR_CLASS);
        const menuWrapper: HTMLElement = document.querySelector(Header.MENU_WRAPPER);
        const navbarBrand: HTMLElement = document.querySelector(Header.NAVBAR_BRAND);
        const navbarToggler:HTMLElement = document.querySelector(Header.NAVBAR_TOGGLER);

        window.onscroll = () => {
            const scrolled: number = window.scrollY;

            if (scrolled > 100) {
                header.classList.add('affix');
                menuWrapper.classList.add('affixMenuWrapper');
                navbarBrand.classList.add('affixClass');
                navbarToggler.classList.add('affixClass');

            } else {
                header.classList.remove('affix');
                menuWrapper.classList.remove('affixMenuWrapper');
                navbarBrand.classList.remove('affixClass');
                navbarToggler.classList.remove('affixClass');
            }
        }
    }

    /**
     *
     */
    private toggleMenu = () => {
        let menuWrapper = document.querySelector(Header.MENU_WRAPPER);
        menuWrapper.classList.toggle('active');
    }

    private childBackBtnListener = (e: Event) => {
        e.preventDefault();
        const btn = e.target as HTMLElement;
        const parent = btn.parentElement.querySelector(".dropdown-menu.sub.toShow");
        if (parent == null) {
            return;
        }

        parent.classList.remove('toShow');
    }

    /**
     *
     */
    private addMainBtnListeners = () => {
        this.topLevelMenu = document.querySelector(Header.TOP_MENU);
        if(this.topLevelMenu == null) {
            return;
        }

        let menuBtns: NodeListOf<Element> = this.topLevelMenu.querySelectorAll(Header.SECOND_LEVEL_MENU);

        // Loop through the buttons and add the active class to the current/clicked button.
        for (var i = 0; i < menuBtns.length; i++) {
            menuBtns[i].addEventListener("click", (e) => {
                e.preventDefault();
                if (this.topLevelMenu.classList.contains("active")) {
                    return;
                }

                let subMenu = e.target as HTMLElement;
                let sub = subMenu.parentElement.querySelector('.sub');

                if (subMenu.classList.contains("back") && sub != null) {
                    return;
                } else if (sub != null) {
                    let backBtn = sub.querySelector(".back") as HTMLElement;
                    backBtn.addEventListener('click', () => {
                        this.childBackBtnListener(e);
                    });

                    sub.classList.add('toShow');

                    let subChild = sub.querySelectorAll('.see-header__secondary-menu');

                    for (var i = 0; i < subChild.length; i++) {
                        subChild[i].addEventListener('click', function (e) {
                            this.querySelector('.sub').classList.add('toShow');
                            let subChildBtn = e.target as HTMLElement;

                            let backBtn = subChildBtn.querySelector(".back") as HTMLElement;
                            backBtn.addEventListener('click', () => {
                                this.childBackBtnListener(e);
                            });
                        })
                    }
                }
            });
        }
    }
}

export const header = new Header();
