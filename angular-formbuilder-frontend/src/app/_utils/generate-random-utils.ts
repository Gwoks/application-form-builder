export class GenerateRandomUtils {

    public static getRandomNumber(maxLength: number): number {
        const rand = Math.floor(Math.random() * Math.floor(maxLength));
        return rand;
    }

    public static getRandomString(maxLength: number): string {
        const charset = 'abcdefghijklmnopqrstuvwxyz1234567890';
        let result = '';

        for (let i = 0; i < maxLength; i++) {
            result += charset[Math.floor(Math.random() * charset.length)];
        }
        return result;
    }
}
