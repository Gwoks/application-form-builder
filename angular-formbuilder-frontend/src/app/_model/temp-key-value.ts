import { KeyValueModel } from './key-value';

export class TempKeyValue {
    public lkpValue: string;
    public options: KeyValueModel[];
    public lkpParent: string;
    public lkpChilds: string[];

    constructor(lkpValue: string, options: KeyValueModel[], lkpParent: string, lkpChilds: string[]) {
        this.lkpValue = lkpValue;
        this.options = options;
        this.lkpParent = lkpParent;
        this.lkpChilds = lkpChilds;
    }
}
