import { Component, OnInit, Input, ViewChildren, QueryList, forwardRef } from '@angular/core';
import { RecursiveTemplateComponent } from '../../recursive-template/recursive-template.component';
import { Application } from '../../../_model/application';
import { FormOutputModel } from '../../../_model/form-output-model';
import { ButtonUtils } from '../../../_utils/button-utils';

@Component({
    selector: 'app-tab',
    templateUrl: './tab.component.html'
})
export class TabComponent implements OnInit {
    @Input() tab: Application;
    @Input() isResetForms: boolean;

    formBodyValue: FormOutputModel[] = [];
    formBodyValidity: boolean[];

    @ViewChildren(forwardRef(() => RecursiveTemplateComponent))
    public recursiveTemplateComponent: QueryList<RecursiveTemplateComponent>;

    constructor() { }

    ngOnInit() {
    }

    getComponentsValue(): FormOutputModel[] {
        return ButtonUtils.getRecursiveComponentValue(
            this.recursiveTemplateComponent, this.formBodyValue);
    }

    getComponentsValidity(): boolean {
        return ButtonUtils.getRecursiveComponentValidity(
            this.recursiveTemplateComponent, this.formBodyValidity);
    }

}
