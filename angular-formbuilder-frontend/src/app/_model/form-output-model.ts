import { KeyValueModel } from './key-value';

export class FormOutputModel {
    public idform: string;
    public idrow: string;
    public type: string;
    public data: KeyValueModel[][];

    constructor(idform: string, idrow: string, type: string, data: KeyValueModel[][]) {
        this.idform = idform;
        this.idrow = idrow;
        this.type = type;
        this.data = data;
    }

    get getData(): KeyValueModel[][] {
        return this.data;
    }

    set setData(value: KeyValueModel[][]) {
        this.data = value;
    }
}
