import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

// component
import { FormbuilderComponent } from './form-builder.component';
import { ApplicationComponent } from './application/application.component';
import { FormComponent } from './components/form/form.component';
import { RecursiveTemplateComponent } from './recursive-template/recursive-template.component';
import { TabComponent } from './components/tab/tab.component';
import { TabsComponent } from './components/tabs/tabs.component';
import { FormInputComponent } from './components/form-input/form-input.component';
import { SearchComponent } from './components/search/search.component';
import { SearchTableComponent } from './components/search/search-table/search-table.component';
import { TableDetailComponent } from './components/table-detail/table-detail.component';
import { FormHeaderDetailComponent } from './components/form-header-detail/form-header-detail.component';
import { FormDetailComponent } from './components/form-detail/form-detail.component';
import { FormsComponent } from './components/forms/forms.component';

// service
import { ApplicationService } from '../_services/application.service';
import { ExecutionService } from '../_services/execution.service';
import { SearchService } from '../_services/search.service';
import { DynamicTableDetailService } from '../_services/dynamic-table-detail.service';
import { InputControlService } from '../_services/input-control.service';
import { LookUpService } from '../_services/lookup.service';
import { Globals } from '../_model/globals';
import { FormsService } from '../_services/forms.service';

const routes: Routes = [
    {
        path: '',
        component: FormbuilderComponent,
        children: [
            { path: 'app/:idapp', component: ApplicationComponent },
            { path: 'app/:idapp/:iddata', component: ApplicationComponent }
        ]
    }
];

@NgModule({
    imports: [
        CommonModule, RouterModule.forChild(routes),
        NgbModule.forRoot(),
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule
    ], declarations: [
        FormbuilderComponent,
        ApplicationComponent,
        FormComponent,
        RecursiveTemplateComponent,
        TabComponent,
        TabsComponent,
        FormInputComponent,
        SearchComponent,
        SearchTableComponent,
        TableDetailComponent,
        FormHeaderDetailComponent,
        FormDetailComponent,
        FormsComponent
    ], providers: [
        ApplicationService,
        ExecutionService,
        SearchService,
        DynamicTableDetailService,
        InputControlService,
        LookUpService,
        Globals,
        FormsService
    ]
})
export class FormbuilderModule {
}
