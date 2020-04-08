export class DocumentReady {
    public ready(callback: () => void) {
        if (document.readyState !== "loading") {
            callback();
        } else if (document.addEventListener) {
            document.addEventListener("DOMContentLoaded", callback);
        }
    }
}
export const seeDocumentReady = new DocumentReady();
