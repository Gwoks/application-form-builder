import { KeyValueModel } from './key-value';

export class SelectedKeyValue {
    public lkpValue: string;
    public selected: string;

    constructor(lkpValue: string, selected: string) {
        this.lkpValue = lkpValue;
        this.selected = selected;
    }
}
