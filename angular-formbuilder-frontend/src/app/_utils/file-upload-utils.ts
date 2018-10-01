export class FileUploadUtils {
    static getFileUploadSize(base64String: string): number {
        let size;
        // size = parseInt(base64String);
        size = base64String.length;
        size = parseInt(size);
        size = Math.round(size * 0.75 / 1024);
        return size;
    }

    static getFileUploadType(stringValue: string): string {
        const fileType: string[] = stringValue.split(':', 3);
        return fileType[1];
    }

    static getFileUploadName(eventName: string): string {
        const cFileName: string[] = eventName.split('.');
        return cFileName[0];
    }
}
