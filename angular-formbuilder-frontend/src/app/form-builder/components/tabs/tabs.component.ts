import { Component, OnInit, Input, ViewChildren, QueryList, forwardRef } from '@angular/core';
import { RecursiveTemplateComponent } from '../../recursive-template/recursive-template.component';
import { Application } from '../../../_model/application';
import { FormOutputModel } from '../../../_model/form-output-model';
import { environment } from '../../../../environments/environment';
import { GenerateRandomUtils } from '../../../_utils/generate-random-utils';
import { ButtonUtils } from '../../../_utils/button-utils';

@Component({
    selector: 'app-tabs',
    templateUrl: './tabs.component.html'
})
export class TabsComponent implements OnInit {
    @Input() tabs: Application;
    @Input() isResetForms: boolean;

    formBodyValue: FormOutputModel[] = [];
    formBodyValidity: boolean[];
    randomGenerate: any = '';

    @ViewChildren(forwardRef(() => RecursiveTemplateComponent))
    public recursiveTemplateComponent: QueryList<RecursiveTemplateComponent>;

    constructor() { }

    ngOnInit() {
        this.randomGenerate = GenerateRandomUtils.getRandomString(environment.maxRangeRandomNumber);
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
