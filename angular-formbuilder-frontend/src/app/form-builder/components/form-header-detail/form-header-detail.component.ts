import { Component, OnInit, Input } from '@angular/core';
// import { Application } from '../../../../../model/application';
// import { FormWrapper } from '../../../../../wrapper/form.wrapper';
import { FormGroup } from '@angular/forms';
import { InputControlService } from '../../../_services/input-control.service';
import { Application } from '../../../_model/application';
import { FormWrapper } from '../../../_wrapper/form.wrapper';
import { ApplicationService } from '../../../_services/application.service';
import { FormOutputModel } from '../../../_model/form-output-model';

@Component({
    selector: 'app-form-header-detail',
    templateUrl: './form-header-detail.component.html',
    providers: [InputControlService]
})
export class FormHeaderDetailComponent implements OnInit {
    @Input() form: Application; // Disini model application di treat sebagai form
    @Input() isResetForms: boolean;

    formWrapper: FormWrapper;
    formGroup: FormGroup;
    initFormValue: any;
    formValidity: boolean[] = [];
    resultString: {};
    tableHeader: any;
    list = [];
    header = [];
    delete: false;
    formHeaderDetailValue = [];
    type = 'header-detail';

    constructor(
        private inputControlService: InputControlService,
        private applicationService: ApplicationService
    ) { }

    ngOnInit() {
        // this.formWrapper = JSON.parse(this.form.value);
        this.formWrapper = this.form.value;
        this.formGroup = this.inputControlService.toFormGroup(this.formWrapper.listFormDetail);
        this.initFormValue = this.formGroup.value;
        // this.tableHeader = JSON.parse(this.form.value);
        this.tableHeader = this.form.value;
        this.header = Object.values(this.tableHeader.listFormDetail);
    }

    saveButton() {
        // switch (this.getFormValid()) {
        switch (true) {
            case true: {
                this.resultString = Object.assign({}, this.formGroup.value);
                this.addToList();
                this.resetAllForm();
                this.getFormValue();
                break;
            }
            default: {
                this.resultString = 'invalid';
                break;
            }
        }
    }

    addToList = function () {
        this.listObject = [];
        for (let x in this.resultString) {
            this.resultString.hasOwnProperty(x) && this.listObject.push(this.resultString[x])
        }
        this.list.push(this.listObject);
    };

    getComponentsValue(): FormOutputModel[] {

        if (JSON.stringify(this.getFormValue()) !== '{}') {
            let temp = new FormOutputModel(
                this.form.id,
                this.applicationService.idData,
                this.type,
                this.getFormValue());

            let formOutputModel: FormOutputModel[] = [];
            formOutputModel.push(temp);

            return formOutputModel;
        }
        return null;
    }

    getFormValue(): any {
        this.formHeaderDetailValue = [];
        for (let i = 0; i < this.list.length; i++) {
            let temp = {};
            for (let j = 0; j < this.header.length; j++) {
                if (this.list[i][j] !== undefined && this.list[i][j] !== '') {
                    temp[this.header[j].labelName] = this.list[i][j];
                }
            }
            this.formHeaderDetailValue.push(temp);
        }
        return this.formHeaderDetailValue;
    }

    deleteItem = function (item) {
        let index = this.list.indexOf(item);
        this.list.splice(index, 1);
        this.delete = true;
    };

    getComponentsValidity(): boolean {
        return this.formGroup.valid;
    }

    resetAllForm() {
        this.formGroup.reset();
    }
}
