import { KeyValueModel } from './key-value';

export class FormDetail {
    guid: string;
    formVersionId: string;
    fieldName;
    string;
    variantType: string;
    length: number;
    labelName: string;
    controlType: string;
    defaultValue: string;
    orderNo: number;
    encrypted: boolean;
    mandatory: boolean;
    readOnly: boolean;
    visible: boolean;
    inputType: string;
    lkpValue: string;
    value: string;
    options: KeyValueModel[];
    allowedType: string;
    allowedSize: number;
    lkpParent: string;
    columnLength: number;
    rowNo: number;
    calculation: boolean;
    parameter: string;
    lkpChilds: string[];
}
