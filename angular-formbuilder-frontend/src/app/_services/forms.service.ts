import { Injectable } from '@angular/core';


@Injectable()
export class FormsService {
    formsRandomNumber: any = '';
    formsRandomString = '';

    setRandomNumber(rand: number) {
        this.formsRandomNumber = rand;
    }

    getRandomNumber(): number {
        return this.formsRandomNumber;
    }

    setRandomString(rand: string) {
        this.formsRandomString = rand;
    }

    getRandomString(): string {
        return this.formsRandomString;
    }
}
