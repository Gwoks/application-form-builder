import { RecursiveTemplateComponent } from '../../recursive-template/recursive-template.component';
import { QueryList, forwardRef } from '@angular/core';
import { Component, OnInit, Input, ViewChildren } from '@angular/core';
import { InputControlService } from '../../../_services/input-control.service';
import { Application } from '../../../_model/application';
import { FormOutputModel } from '../../../_model/form-output-model';
import { ApplicationService } from '../../../_services/application.service';
import { ButtonUtils } from '../../../_utils/button-utils';

@Component({
    selector: 'app-form-form-detail',
    templateUrl: './form-detail.component.html',
    providers: [InputControlService]
})

export class FormDetailComponent implements OnInit {
    @Input() form: Application; // Disini model application di treat sebagai form

    formBodyValue: FormOutputModel[] = [];
    formBodyValidity: boolean[];
    listData = [];
    type = 'form-detail';
    idData: string;
    isResetForms = false;

    @ViewChildren(forwardRef(() => RecursiveTemplateComponent))
    public recursiveTemplateComponent: QueryList<RecursiveTemplateComponent>;

    constructor(private applicationService: ApplicationService) { }

    ngOnInit() {
        this.initListData();

        this.idData = this.form.id_row;
    }

    addData() {
        // if state new, maka arraynya ditambahkan dengan form apapun /  ke 0
        // else, array ditambahkan dengan form kosong
        if (this.applicationService.isStateNew) {
            this.listData.push(this.listData[0]);
        } else {
            this.isResetForms = true;
            this.listData.push(this.listData[0]);
        }

    }

    deleteData(item: number) {
        // supaya array terkhir tidak dihapus
        if (this.listData.length > 1) {
            let index = this.listData.indexOf(item);
            this.listData.splice(index, 1);
        }
    }

    getComponentsValue(): FormOutputModel[] {
        return ButtonUtils.getRecursiveComponentValue(
            this.recursiveTemplateComponent, this.formBodyValue);
    }

    getComponentsValidity(): boolean {
        return ButtonUtils.getRecursiveComponentValidity(
            this.recursiveTemplateComponent, this.formBodyValidity);
    }

    initListData(): void {
        this.form.childs.forEach(element => {
            let temp: Application[] = [element];
            this.listData.push(temp);
        });
    }
}
