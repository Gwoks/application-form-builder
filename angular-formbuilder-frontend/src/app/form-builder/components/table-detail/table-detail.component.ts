import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TableContentModel } from '../../../_model/table/table-content';
import { DynamicTableDetailService } from '../../../_services/dynamic-table-detail.service';
import { Globals } from '../../../_model/globals';
import { KeyValueModel } from '../../../_model/key-value';

@Component({
    selector: 'app-table-detail',
    templateUrl: './table-detail.component.html'
})
export class TableDetailComponent implements OnInit {
    @Output() rowValue = new EventEmitter();
    ERR_ = 'PARAMETER NOT FOUND';
    tableModel: TableContentModel[];
    idLookup = '';

    constructor(
        private dynamicTableDetailService: DynamicTableDetailService,
        private globals: Globals,
    ) { }

    ngOnInit() {
    }

    getTableDetailContent(idLookup: string, parameter: string, rand: any): void {
        this.dynamicTableDetailService.getLovByQuery(idLookup, getParams(this.globals, parameter, rand))
            .subscribe(content => {
                this.tableModel = content;
            });
    }

    onRowClick(value: KeyValueModel[]) {
        this.rowValue.emit(value);
    }

}

function getParams(globals: Globals, parameter: string, rand: any): KeyValueModel[] {
    let result: KeyValueModel[] = [];
    if (parameter != null) {
        let parameters: any[] = parameter.split(';');

        parameters.forEach(element => {
            if (element !== '') {
                // fieldname untuk di lookup = fieldname + random (ex: business_unit0XKS)
                // kalau gk ketemu, maka fieldname = fieldname (ex: business_unit)
                let fieldname = element + rand;
                let temp: KeyValueModel = globals.getLookupFormInputKeyValue(fieldname);
                if (temp === undefined) {
                    temp = globals.getLookupFormInputKeyValue(element);
                }

                if (temp !== null && temp !== undefined) {
                    let resultElement: KeyValueModel = new KeyValueModel(element, temp.value);
                    result.push(resultElement);
                }
            }
        });
    }
    return result;
}
