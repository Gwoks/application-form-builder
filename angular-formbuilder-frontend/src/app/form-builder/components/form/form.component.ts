import { FormInputComponent } from './../form-input/form-input.component';
import { Component, OnInit, Input, QueryList, ViewChildren } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { InputControlService } from '../../../_services/input-control.service';
import { ExecutionService } from '../../../_services/execution.service';
import { Application } from '../../../_model/application';
import { FormWrapper } from '../../../_wrapper/form.wrapper';
import { ResponseModel } from '../../../_model/response';
import { FormOutputModel } from '../../../_model/form-output-model';
import { ApplicationService } from '../../../_services/application.service';
import { KeyValueModel } from '../../../_model/key-value';
import { ButtonUtils } from '../../../_utils/button-utils';
import { environment } from '../../../../environments/environment';

@Component({
    selector: 'app-form',
    templateUrl: './form.component.html',
    providers: [InputControlService, ExecutionService]
})
export class FormComponent implements OnInit {
    @Input() form: Application; // Disini model application di treat sebagai form
    @Input() isResetForms: boolean;

    formWrapper: FormWrapper;
    formGroup: FormGroup;
    initFormValue: any;
    formOutput: FormOutputModel[] = [];

    formBodyValue: FormOutputModel[] = [];
    formValidity: boolean[] = [];
    resultString = '';
    type = 'form';
    responseModel: ResponseModel;
    componentState: string;
    enviromentProd: boolean;

    constructor(
        private inputControlService: InputControlService,
        private executionService: ExecutionService,
        private applicationService: ApplicationService,
        private router: Router) {
    }

    ngOnInit() {
        this.formWrapper = this.form.value;
        this.formGroup = this.inputControlService.toFormGroup(this.formWrapper.listFormDetail);
        this.initFormValue = this.formGroup.value;
        this.enviromentProd = environment.production;

        // kalo reset form true, maka reset all form
        // if (this.isResetForms) {
        //     this.formInputComponent.forEach(formInput =>
        //         formInput.clearFile()
        //     );
        // }

        // state
        if (this.applicationService.isStateNew) {
            this.componentState = 'new';
        } else {
            this.componentState = 'edit';
        }
    }

    submitButton(idExect: string, isValidateFirst: any, pageAction: string) {

        isValidateFirst = JSON.parse(isValidateFirst);
        switch (this.getFormValid() || !isValidateFirst) {
            case true: {

                this.resultString = JSON.stringify(this.getComponentsValue());

                // this.formGroupChildComponents.forEach(childComponent => childComponent.resetAllForm());
                switch (this.componentState) {
                    case 'new':
                        this.executionService.postExecute(idExect, this.resultString)
                            .subscribe(responsemodel => this.router.navigate([pageAction, responsemodel.row]));
                        break;
                    default:
                        ButtonUtils.submitHttp(this.resultString, idExect, this.executionService);
                        break;
                }
                break;
            }
            default: {
                this.resultString = 'invalid';
                break;
            }
        }
    }

    getComponentsValue(): FormOutputModel[] {
        if (JSON.stringify(this.getFormValue()) !== '{}') {
            // CEK LAGI ID DATA MSH NULL
            this.formOutput =
                ButtonUtils.createOutputModel(this.form.id, this.applicationService.idData, this.type, this.formGroup.value);
            return this.formOutput;
        }
        return null;
    }

    getFormValue(): KeyValueModel[] {
        // deleteEmptyFormValue(this.initFormValue, this.formGroup.value);
        deleteSeparator(this.initFormValue, this.formGroup.value);
        return this.formGroup.value;
    }

    getAllFormValue(): KeyValueModel[] {
        return this.formGroup.value;
    }

    getFormValid(): boolean {
        return this.formGroup.valid;
    }

    resetAllForm() {
        this.formGroup.reset();
    }

    getState(buttonState: string): boolean {
        if (buttonState === this.componentState || buttonState === 'all') {
            return true;
        } else {
            return false;
        }
    }

}

function deleteEmptyFormValue(initValue: any, resultValue: any): void {
    for (let key in resultValue) {
        if (initValue[key] !== resultValue[key] && resultValue[key] != null) {
            continue;
        } else {
            delete resultValue[key];
        }
    }
}

function deleteSeparator(initValue: any, resultValue: any): void {
    for (let key in resultValue) {
        if (key === 'separator') {
            delete resultValue[key];
        } else {
            continue;
        }
    }
}
