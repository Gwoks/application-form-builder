import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../../../../_model/application';
import { SearchDTO } from '../../../../_model/search-dto';
import { SearchService } from '../../../../_services/search.service';

@Component({
    selector: 'app-search-table',
    templateUrl: './search-table.component.html'
})
export class SearchTableComponent implements OnInit {
    @Input() inqrtable: Application;
    tableHeader: string[];
    tableContent: SearchDTO[];

    constructor(
        private inqrService: SearchService
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
