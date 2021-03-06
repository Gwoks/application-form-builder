import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { SearchTableComponent } from './search-table/search-table.component';
import { Application } from '../../../_model/application';
import { KeyValueModel } from '../../../_model/key-value';
import { environment } from '../../../../environments/environment';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
    @Input() inqr: Application;
    @Input() isResetForms: boolean;

    @ViewChild(FormComponent) formComponent: FormComponent;
    @ViewChild(SearchTableComponent) inqrTableComponent: SearchTableComponent;

    temp = "{'onboard.usrprf.first_name':'asdasd','onboard.usrprf.last_name':'asdasd'}";
    resultString = '';
    resModel: KeyValueModel[] = [];
    enviromentProd: boolean;

    constructor() { }

    ngOnInit() {
        // console.log(this.inqr);
        this.enviromentProd = environment.production;
    }

    submitButton(idExecution: string) {

        if (this.formComponent.getFormValid() === true) {
            this.resultString = JSON.stringify(this.formComponent.getFormValue());
            this.inqrTableComponent.searchTableContent(this.resultString);
        } else {
            this.resultString = 'invalid';
            console.log(JSON.stringify(this.formComponent.getFormValue()));
        }
    }
}
