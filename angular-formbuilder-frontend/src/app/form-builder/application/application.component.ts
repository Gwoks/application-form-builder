import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Application } from '../../_model/application';
import { ApplicationService } from '../../_services/application.service';

@Component({
    selector: 'app-application',
    templateUrl: './application.component.html'
})
export class ApplicationComponent implements OnInit {

    private sub: any;
    application: Application[];
    idApp: string;
    idData: string;
    firstApplication: Application;

    constructor(
        private route: ActivatedRoute,
        private applicationService: ApplicationService
    ) { }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            this.idApp = params['idapp'];
            this.applicationService.idApplication = this.idApp;
            this.getFormData(params);
        });
    }

    getFormData(params: any): void {
        this.idData = params['iddata'];

        if (this.idData != null) {
            this.applicationService.getAppIdByDataId(this.idApp, this.idData).subscribe(data => {
                this.application = data;
            });
            // set idData ke service untuk di submit
            this.applicationService.idData = this.idData;
        } else {
            this.applicationService.getAppById(this.idApp).subscribe(data => {
                this.application = data;
            });
            this.applicationService.idData = null;
        }
    }
}
