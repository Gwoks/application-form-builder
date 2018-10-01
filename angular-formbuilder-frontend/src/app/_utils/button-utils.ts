// import { FormHeaderDetailComponent } from './../theme/pages/formbuilder/components/form-header-detail/form-header-detail.component';
import { QueryList } from '@angular/core';
import { FormOutputModel } from '../_model/form-output-model';
import { KeyValueModel } from '../_model/key-value';
// import { FormComponent } from '../theme/pages/formbuilder/components/form/form.component';
// import { TabsComponent } from '../theme/pages/formbuilder/components/tabs/tabs.component';
// import { TabComponent } from '../theme/pages/formbuilder/components/tab/tab.component';
// import { RecursiveTemplateComponent } from '../theme/pages/formbuilder/recursive-template/recursive-template.component';
import { ExecutionService } from '../_services/execution.service';
import { FormComponent } from '../form-builder/components/form/form.component';
import { TabsComponent } from '../form-builder/components/tabs/tabs.component';
import { TabComponent } from '../form-builder/components/tab/tab.component';
import { FormHeaderDetailComponent } from '../form-builder/components/form-header-detail/form-header-detail.component';
import { FormDetailComponent } from '../form-builder/components/form-detail/form-detail.component';
import { FormsComponent } from '../form-builder/components/forms/forms.component';
import { RecursiveTemplateComponent } from '../form-builder/recursive-template/recursive-template.component';
// import { FormDetailComponent } from '../theme/pages/formbuilder/components/form-detail/form-detail.component';

export class ButtonUtils {

    public static getFormBodyValidity(
        formComponent: QueryList<FormComponent>,
        tabsComponent: QueryList<TabsComponent>,
        tabComponent: QueryList<TabComponent>,
        formHeaderDetailComponent: QueryList<FormHeaderDetailComponent>,
        formDetailComponent: QueryList<FormDetailComponent>,
        formsComponent: QueryList<FormsComponent>,
        formValidity: boolean[]): boolean {

        formValidity = [];

        formComponent.forEach(
            child =>
                formValidity.push(
                    child.getFormValid()));

        tabsComponent.forEach(
            tabs =>
                formValidity.push(
                    tabs.getComponentsValidity()));

        tabComponent.forEach(
            tab =>
                formValidity.push(
                    tab.getComponentsValidity()));

        formHeaderDetailComponent.forEach(
            formHeaderDetail =>
                formValidity.push(
                    formHeaderDetail.getComponentsValidity()));

        formDetailComponent.forEach(
            formDetail =>
                formValidity.push(
                    formDetail.getComponentsValidity()));

        formsComponent.forEach(
            forms =>
                formValidity.push(
                    forms.getComponentsValidity()));

        if (formValidity.indexOf(false) > -1) {
            return false;
        }
        return true;
    }

    public static getFormBodyValue(
        formComponent: QueryList<FormComponent>,
        tabsComponent: QueryList<TabsComponent>,
        tabComponent: QueryList<TabComponent>,
        formHeaderDetailComponent: QueryList<FormHeaderDetailComponent>,
        formDetailComponent: QueryList<FormDetailComponent>,
        formsComponent: QueryList<FormsComponent>,
        formBodyValue: FormOutputModel[]): FormOutputModel[] {

        formBodyValue = [];
        formComponent.forEach(
            form =>
                this.checkIsFormOutputNull(
                    form.getComponentsValue(), formBodyValue)
        );

        tabsComponent.forEach(
            tabs =>
                this.checkIsFormOutputNull(
                    tabs.getComponentsValue(), formBodyValue)
        );

        tabComponent.forEach(
            tab =>
                this.checkIsFormOutputNull(
                    tab.getComponentsValue(), formBodyValue)
        );

        formHeaderDetailComponent.forEach(
            formHeaderDetail =>
                this.checkIsFormOutputNull(
                    formHeaderDetail.getComponentsValue(), formBodyValue)
        );

        formDetailComponent.forEach(
            formDetail =>
                this.checkIsFormOutputNull(
                    formDetail.getComponentsValue(), formBodyValue)
        );

        formsComponent.forEach(
            forms =>
                this.checkIsFormOutputNull(
                    forms.getComponentsValue(), formBodyValue)
        );

        return formBodyValue;
    }

    public static getRecursiveComponentValidity(
        recComponent: QueryList<RecursiveTemplateComponent>,
        formValidity: boolean[]): boolean {
        formValidity = [];
        recComponent.forEach(
            child =>
                formValidity.push(
                    child.getFormBodyValidity())
        );
        if (formValidity.indexOf(false) > -1) {
            return false;
        }
        return true;
    }

    public static getRecursiveComponentValue(
        recursiveTemplateComponent: QueryList<RecursiveTemplateComponent>,
        formBodyValue: FormOutputModel[]): FormOutputModel[] {
        formBodyValue = [];
        recursiveTemplateComponent.forEach(
            childComponent =>
                ButtonUtils.checkIsFormOutputNull(
                    childComponent.getFormBodyValue(), formBodyValue)
        );
        return formBodyValue;
    }

    public static getFormsComponentValue(
        formsComponent: QueryList<FormsComponent>,
        formBodyValue: FormOutputModel[]): FormOutputModel[] {
        formBodyValue = [];
        formsComponent.forEach(
            childComponent => {
                console.log(`kepanggil nih form-detail component`);
                ButtonUtils.checkIsFormOutputNull(
                    childComponent.getComponentsValue(), formBodyValue);
            });
        return formBodyValue;
    }

    public static getFormsComponentValidity(
        formsComponent: QueryList<FormsComponent>,
        formsValidity: boolean[]): boolean {
        formsValidity = [];
        formsComponent.forEach(
            child => {
                formsValidity.push(child.getComponentsValidity());
            });

        if (formsValidity.indexOf(false) > -1) {
            return false;
        }
        return true;
    }

    public static createOutputModel(
        idForm: string,
        idData: string,
        type: string,
        value: KeyValueModel[]): FormOutputModel[] {
        let tempKeyValue: KeyValueModel[][] = [];
        tempKeyValue.push(value);
        let temp: FormOutputModel = new FormOutputModel(idForm, idData, type, tempKeyValue);
        let arr: FormOutputModel[] = [];
        this.generateFormGroupParentValue(temp, arr);
        return arr;
    }

    public static generateFormGroupParentValue(value: FormOutputModel, arr: FormOutputModel[]) {
        // if (value != null) {
        arr.push(value);
        // }
    }

    public static checkIsFormOutputNull(value: FormOutputModel[], arr: FormOutputModel[]) {
        // if (value != null) {
        for (let i = 0; i < value.length; i++) {
            arr.push(value[i]);
        }
        // }
    }

    public static submitHttp(
        value: string,
        idExecution: string,
        executionService: ExecutionService) {
        executionService.postExecute(idExecution, value).subscribe();
    }
}



