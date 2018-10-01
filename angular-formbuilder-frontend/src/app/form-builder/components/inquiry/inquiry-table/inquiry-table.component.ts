import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../../../../_model/application';
import { InquiryDTO } from '../../../../_model/inquiry-dto';
import { InquiryService } from '../../../../_services/inquiry.service';

@Component({
    selector: 'app-inquiry-table',
    templateUrl: './inquiry-table.component.html'
})
export class InquiryTableComponent implements OnInit {
    @Input() inqrtable: Application;
    tableHeader: string[];
    tableContent: InquiryDTO[];

    constructor(
        private inqrService: InquiryService
    ) { }

    ngOnInit() {
        this.tableHeader = JSON.parse(this.inqrtable.header_value);
        this.getTableContent();
    }

    getTableContent(): void {
        this.inqrService.getTableDetailById(this.inqrtable.id)
            .subscribe(content => this.tableContent = content);
    }

    searchTableContent(value: string) {
        this.inqrService.searchTableContainer(this.inqrtable.id, value)
            .subscribe(content => this.tableContent = content);
    }
}
