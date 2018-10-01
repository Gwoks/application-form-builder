import { FormsComponent } from './../components/forms/forms.component';
import { FormDetailComponent } from './../components/form-detail/form-detail.component';
import { Router } from '@angular/router';
import { Component, OnInit, Input, ViewChildren, QueryList } from '@angular/core';
import { FormComponent } from '../components/form/form.component';
import { TabsComponent } from '../components/tabs/tabs.component';
import { TabComponent } from '../components/tab/tab.component';
import { FormHeaderDetailComponent } from '../components/form-header-detail/form-header-detail.component';
import { Application } from '../../_model/application';
import { FormOutputModel } from '../../_model/form-output-model';
import { ResponseModel } from '../../_model/response';
import { ExecutionService } from '../../_services/execution.service';
import { ApplicationService } from '../../_services/application.service';
import { Globals } from '../../_model/globals';
import { environment } from '../../../environments/environment.prod';
import { ButtonUtils } from '../../_utils/button-utils';

@Component({
    selector: 'app-recursive-template',
    templateUrl: './recursive-template.component.html'
})
export class RecursiveTemplateComponent implements OnInit {

    @Input() application: Application;
    @Input() isResetForms: boolean;

    componentState: string;

    formBodyValue: FormOutputModel[] = [];
    formValidity: boolean[] = [];
    resultString = '';
    responseModel: ResponseModel;
    enviromentProd: boolean;

    @ViewChildren(FormComponent)
    public formComponent: QueryList<FormComponent>;

    @ViewChildren(TabsComponent)
    public tabsComponent: QueryList<TabsComponent>;

    @ViewChildren(TabComponent)
    public tabComponent: QueryList<TabComponent>;

    @ViewChildren(FormHeaderDetailComponent)
    public headerDetailComponent: QueryList<FormHeaderDetailComponent>;

    @ViewChildren(FormDetailComponent)
    public formDetailComponent: QueryList<FormDetailComponent>;

    @ViewChildren(FormsComponent)
    public formsComponent: QueryList<FormsComponent>;


    constructor(private executionService: ExecutionService,
        private applicationService: ApplicationService,
        private router: Router,
        private globals: Globals) { }

    ngOnInit() {
        this.enviromentProd = environment.production;

        // state
        if (this.applicationService.isStateNew) {
            this.componentState = 'new';
        } else {
            this.componentState = 'edit';
        }
    }

    submitButton(idExect: string, isValidateFirst: any, pageAction: string) {
        isValidateFirst = JSON.parse(isValidateFirst);
        switch (this.getFormBodyValidity() || !isValidateFirst) {
            case true: {

                this.resultString = JSON.stringify(this.getFormBodyValue());

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

    getState(buttonState: string): boolean {
        if (buttonState === this.componentState || buttonState === 'all') {
            return true;
        } else {
            return false;
        }
    }

    getFormBodyValidity(): boolean {
        return ButtonUtils.getFormBodyValidity(
            this.formComponent, this.tabsComponent,
            this.tabComponent, this.headerDetailComponent,
            this.formDetailComponent,
            this.formsComponent,
            this.formValidity
        );
    }

    getFormBodyValue(): FormOutputModel[] {
        return ButtonUtils.getFormBodyValue(
            this.formComponent, this.tabsComponent,
            this.tabComponent, this.headerDetailComponent,
            this.formDetailComponent,
            this.formsComponent,
            this.formBodyValue
        );
    }

}
