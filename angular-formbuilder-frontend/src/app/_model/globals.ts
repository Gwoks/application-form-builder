import { Injectable } from '@angular/core';
import { TempKeyValue } from './temp-key-value';
import { KeyValueModel } from './key-value';

@Injectable()
export class Globals {
    role = 'test';

    tempKeyValues: TempKeyValue[] = [];

    lookupFormInputs: any[] = [];
    lookupFormInputKeyValue: KeyValueModel[] = [
        new KeyValueModel('office_id', '9999999999')
    ];
    lookUps: KeyValueModel[] = [
        {
            key: '01',
            value: 'Value01'
        },
        {
            key: '02',
            value: 'Value02'
        },
        {
            key: '03',
            value: 'Value03'
        }
    ];

    changedRole() {
        console.log('length = ' + this.tempKeyValues.length);
    }

    getLookupFormInputListByFieldName(fieldName: string): {} {
        let result = '';
        this.lookupFormInputs.forEach(element => {
            for (let key in element) {
                if (fieldName === key) {
                    result = element;
                }
            }
        });
        return result;
    }

    getLookupFormInputKeyValue(fieldName: string): KeyValueModel {
        let result: KeyValueModel;
        this.lookupFormInputKeyValue.forEach(element => {
            if (element.key === fieldName) {
                result = element;
            }
        });
        return result;
    }
    pushLookupFormInputKeyValue(value: KeyValueModel) {
        let index: number = null;

        this.lookupFormInputKeyValue.forEach(element => {
            if (element.key === value.key) {
                index = this.lookupFormInputKeyValue.indexOf(element);
            }
        });

        if (index > -1 && index != null) {
            this.lookupFormInputKeyValue.splice(index, 1);
        }
        this.lookupFormInputKeyValue.push(value);
    }

    pushLookupFormInputList(value: any) {
        let keyFromValue = '';
        // let valueFromValue = '';
        let index: number = null;
        for (let key in value) {
            keyFromValue = key;
            // valueFromValue = value[key];
        }
        // console.log('awa' + JSON.stringify(this.lookupFormInputs));
        // console.log(keyFromValue + ' key valie ' + valueFromValue);
        this.lookupFormInputs.forEach(element => {
            // if (JSON.stringify(element) == JSON.stringify(value)) {
            //     index = this.lookupFormInput.indexOf(element);
            //     console.log('ketemu')
            // }
            for (let key in element) {
                if (key === keyFromValue) {
                    // console.log(key + ' element ' + keyFromValue);
                    index = this.lookupFormInputs.indexOf(element);
                    // console.log('index ' + index);
                    // element[key] = valueFromValue;
                }
                // for (let keyFromInput in value) {
                //     if (keyFromInput == key) {
                //         console.log(keyFromInput + ' ' + key)
                //         index = key;
                //     }
                // }
            }
        });

        if (index > -1 && index != null) {
            this.lookupFormInputs.splice(index, 1);
        }
        this.lookupFormInputs.push(value);
        // console.log(JSON.stringify(this.lookupFormInputs));
    }

    setTempKeyValueModel(lkpValue: string, options: KeyValueModel[], lkpParent: string, lkpChilds: string[]): void {
        let temp: TempKeyValue = new TempKeyValue(lkpValue, options, lkpParent, lkpChilds);
        this.tempKeyValues.push(temp);
        console.log(this.tempKeyValues);
    }

    getLookUpsByEvent(lkpValue: string): KeyValueModel[] {
        // for (var i = 0; i < this.tempKeyValues.length; i++) {
        //     if (this.tempKeyValues[i].lkpValue === lkpValue) {
        //         return this.tempKeyValues[i].options;
        //     }
        // }

        // console.log(lkpValue);
        // console.log(this.tempKeyValues);

        let found = this.tempKeyValues.find(x => x.lkpValue === lkpValue);
        if (found !== undefined) {
            return found.options;
        }

        // return this.tempKeyValues.find(x => x.lkpValue === lkpValue).options;



        // console.log('masuk = ' + lkpValue);

        // return null;
    }

}
