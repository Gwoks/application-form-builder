import { Component, OnInit, Input, ViewChildren, QueryList, forwardRef } from '@angular/core';
import { RecursiveTemplateComponent } from '../../recursive-template/recursive-template.component';
import { FormsService } from '../../../_services/forms.service';
import { Application } from '../../../_model/application';
import { FormOutputModel } from '../../../_model/form-output-model';
import { ApplicationService } from '../../../_services/application.service';
import { environment } from '../../../../environments/environment';
import { GenerateRandomUtils } from '../../../_utils/generate-random-utils';
import { ButtonUtils } from '../../../_utils/button-utils';

@Component({
    selector: 'app-forms',
    templateUrl: './forms.component.html',
    providers: [FormsService]
})
export class FormsComponent implements OnInit {
    @Input() forms: Application;
    @Input() isResetForms: boolean;

    formBodyValue: FormOutputModel[] = [];
    formBodyValidity: boolean[];
    numberData: number;
    listData = [];
    type = 'form-detail';
    idData: string;

    @ViewChildren(forwardRef(() => RecursiveTemplateComponent))
    public recursiveTemplateComponent: QueryList<RecursiveTemplateComponent>;

    constructor(private formsService: FormsService,
        private applicationService: ApplicationService) { }

    ngOnInit() {
        // generate and set random number to service
        const rand = GenerateRandomUtils.getRandomString(environment.maxRangeRandomNumber);
        this.formsService.setRandomString(rand);

        this.numberData = this.forms.childs.length;
        this.listData.push(this.numberData);
        this.idData = this.applicationService.idData;

    }

    getComponentsValue(): FormOutputModel[] {
        this.formBodyValue = [];
        this.formBodyValue =
            this.groupDataValue(this.formBodyValue, ButtonUtils.getRecursiveComponentValue(
                this.recursiveTemplateComponent, this.formBodyValue));
        return this.formBodyValue;
    }

    getComponentsValidity(): boolean {
        return ButtonUtils.getRecursiveComponentValidity(
            this.recursiveTemplateComponent, this.formBodyValidity);
    }

    groupDataValue(result: FormOutputModel[], value: FormOutputModel[]): FormOutputModel[] {
        let listIdForm: string[] = [];
        let listType: string[] = [];
        let listIDData: string[] = [];
        let dataArr = [];

        getListForminArray(listIdForm, listType, listIDData, value);

        getDataAray(listIdForm, value, dataArr);

        let idRow = this.forms.id.substring(6);
        pushDataToResult(value, dataArr, listType, result, this.listData, idRow, this.idData, this.type);

        return result;
    }
}

function getDataAray(listIdForm: string[], value: FormOutputModel[], dataArr: any[]): void {
    for (let i = 0; i < listIdForm.length; i++) {
        let dataTemp = [];
        for (let x = 0; x < value.length; x++) {
            if (value[x].idform === listIdForm[i]) {
                dataTemp.push(value[x].data[0]);
            }
        }
        dataArr.push(dataTemp);
    }
}

function pushDataToResult(value: FormOutputModel[], dataArr: any[], listType: string[],
    result: FormOutputModel[], listData: any[], idRow: string, idData: string, type: string): void {
    for (let x = 0; x < listData.length; x++) {
        let dataTemp = [];
        for (let y = 0; y < dataArr.length; y++) {
            let temp = [];
            temp.push(dataArr[y][x]);
            dataTemp.push(new FormOutputModel(value[y].idform, idRow, listType[y], temp));
        }
        result.push(new FormOutputModel(null, idData, type, dataTemp));
    }
}

function getListForminArray(listIdForm: string[], listType: string[],
    listIDData: string[], value: FormOutputModel[]): void {
    for (let index = 0; index < value.length; index++) {
        if (!listIdForm.includes(value[index].idform)) {
            listIdForm.push(value[index].idform);
            listType.push(value[index].type);
            listIDData.push(value[index].idrow)
        }
    }
}
